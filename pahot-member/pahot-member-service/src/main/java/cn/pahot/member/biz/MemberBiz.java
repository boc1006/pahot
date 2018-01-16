package cn.pahot.member.biz;

import com.boc.common.core.biz.BaseBiz;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.boc.common.biz.IdWorkerUtil;
import com.boc.common.enums.PropertiesHelper;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.utils.DateUtils;
import com.boc.common.utils.MD5Helper;

import cn.pahot.logger.entity.BizLogEntity;
import cn.pahot.member.consts.MemberConsts;
import cn.pahot.member.dao.MemberMapper;
import cn.pahot.member.entity.AccountEntity;
import cn.pahot.member.entity.MemberWXBindEntity;
import cn.pahot.member.enums.RegTypeEnum;
import cn.pahot.member.exception.MemberException;
import cn.pahot.message.consts.SmsChannelEnum;
import cn.pahot.message.facade.SmsFacade;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.facade.CapXaFacade;

/**
 * 会员账号管理业务逻辑实现
 * <p>@Title: MemberBiz.java 
 * <p>@Package cn.pahot.member.biz 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月18日 上午11:48:07 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("memberBiz")
public class MemberBiz extends BaseBiz{
	private final Logger logger = Logger.getLogger(MemberBiz.class);
	@Autowired
	private MemberMapper memberDao;
	
	@Autowired
	private SmsFacade smsFacade;

	/**
	 * 会员注册,提供给业务内部调用,自动注册会员账号,如果存在该用,则直接返回用户ID
	 * @param phone 手机号码【必传】
	 * @param isDefPwd 是否生成一个默认密码【必传】
	 * @param passwd 密码,当isDefPwd=false时该参数必传
	 * @param isSendSms 是否需要给用户发送短信通知,并发送该账号的初始密码【必传】
	 * @param regType 注册方式,枚举定义cn.pahot.member.enums.RegTypeEnum【必传】
	 * @param terminal 注册终端,枚举定义cn.pahot.member.enums.TerminalEnum【必传】
	 * @param name 用户真实姓名【非必传】
	 * @param tel 用户联系方式【非必传】
	 * @return 注册成功则返回该会员的ID
	 * @throws BizException
	 */
	public AccountEntity bizRegistryMember(String phone,boolean isDefPwd,String passwd,boolean isSendSms,String regType,String terminal,String name,String tel) {
		if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(regType) || StringUtils.isEmpty(terminal)) {
			throw MemberException.PARAM_IS_NULL.newInstance("bizRegistryMember()->phone|regType|terminal");
		}
		
		if(!isDefPwd && StringUtils.isEmpty(passwd)) {
			throw MemberException.PARAM_IS_NULL.newInstance("bizRegistryMember()->passwd");
		}
		
		if(isDefPwd) {
			passwd = MemberConsts.INIT_MEMBER_PASSWD;
		}
		DTO<String,String> dto = new BaseDTO<String,String>();
		dto.put("username", phone);
		AccountEntity account = memberDao.getMemberByPhoneOrId(dto);
		if(account != null) {
			return account;
		}
		account = new AccountEntity();
		account.setUsername(phone);
		account.setPassword(MD5Helper.encrypt(passwd));
		account.setName(name);
		account.setTel(tel);
		account.setType(regType);
		account.setTerminal(terminal);
		account.setRegtime(DateUtils.getCurrDateTimeToLong());
		memberDao.registryMember(account);
		if(isSendSms) {//发送短信给会员
			JSONObject memberRegistryContent = new JSONObject();
			//TODO 设置短信模板内容
			memberRegistryContent.put("username", phone);
			memberRegistryContent.put("passwd", passwd);
			try {
				smsFacade.sendBizSms(MemberConsts.MEMBER_REGISTRY_TEMP, 
						SmsChannelEnum.CHANNEL_ALIDY.key, PropertiesHelper.SYSTEM_ID_MEMBER, 
						phone, memberRegistryContent.toJSONString(), "{}", "通过业务自动注册会员", 3, 0);
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage());
			}
		}
		return account;
	}
	
	/**
	 * 会员账号与微信信息绑定
	 * @param entity 绑定实体对象
	 * <p>entity.aid 会员编号【必传】</p>
	 * <p>entity.openid 微信-openId【必传】</p>
	 * <p>entity.unionid 微信-unionid【必传】</p>
	 * <p>entity.nickname 微信-用户昵称【非必传】</p>
	 * <p>entity.sex 微信-用户性别【非必传】</p>
	 * <p>entity.province 微信-省份【非必传】</p>
	 * <p>entity.city 微信-城市【非必传】</p>
	 * <p>entity.country 微信-国家【非必传】</p>
	 * <p>entity.headimgurl 微信-用户头像url【非必传】</p>
	 * <p>entity.privilegr 微信-用户特权信息(json数组)【非必传】</p>
	 * @throws BizException
	 */
	public void bindMemberWX(MemberWXBindEntity entity) {
		if(entity == null) {
			throw MemberException.PARAM_IS_NULL.newInstance("绑定会员与微信账号->entity");
		}
		
		if(entity.getAid() == null && entity.getAid() == 0 || 
				StringUtils.isEmpty(entity.getOpenid()) || 
				StringUtils.isEmpty(entity.getUnionid())) {
			throw MemberException.PARAM_IS_NULL.newInstance("绑定会员与微信账号->memberId|openId|unionId");
		}
		
		//查看该微信是否已绑定过
		DTO<String,String> bindDTO = new BaseDTO<String,String>();
		bindDTO.put("unionid", entity.getUnionid());
		MemberWXBindEntity wxBind = memberDao.getMemberWXBind(bindDTO);
		if(wxBind !=null) {
			throw MemberException.PARAM_HINT_WARN.newInstance("该微信已被其它会员绑定,不能再次绑定!unionid="+entity.getUnionid());
		}

		DTO<String,String> dto = new BaseDTO<String,String>();
		dto.put("id", String.valueOf(entity.getAid()));
		AccountEntity account = memberDao.getMemberByPhoneOrId(dto);
		if(account == null) {
			throw MemberException.PARAM_HINT_ERROR.newInstance("该会员账号不存在,id="+entity.getAid());
		}
		
		entity.setId(IdWorkerUtil.getId());
		entity.setBindtime(DateUtils.getCurrDateTimeToLong());
		memberDao.saveMemberWXBind(entity);
	}
	
	/**
	 * 会员注册
	 * @param phone 手机号码【必传】
	 * @param passwd 密码(明文)【必传】
	 * @param name 会员真实姓名【非必传】
	 * @param tel 联系方式【非必传】
	 * @param terminal 注册终端,枚举定义cn.pahot.member.enums.TerminalEnum【必传】
	 * @return 新注册会员ID
	 * @throws BizException 如果用户注册失败则抛出异常,包括用户已注册
	 */
	public int registryMember(String phone,String passwd,String name,String tel,String terminal) {
		if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(passwd) || StringUtils.isEmpty(terminal)) {
			throw MemberException.PARAM_IS_NULL.newInstance("会员注册->phone|passwd|terminal");
		}
		
		DTO<String,String> dto = new BaseDTO<String,String>();
		dto.put("username", phone);
		AccountEntity account = memberDao.getMemberByPhoneOrId(dto);
		if(account != null) {
			throw MemberException.PARAM_HINT_WARN.newInstance("会员注册->手机号码"+phone+"已注册!");
		}
		
		account = new AccountEntity();
		account.setUsername(phone);
		account.setPassword(MD5Helper.encrypt(passwd));
		account.setName(name);
		account.setTel(tel);
		account.setType(RegTypeEnum.REG_TYPE_01.key);
		account.setTerminal(terminal);
		account.setRegtime(DateUtils.getCurrDateTimeToLong());
		memberDao.registryMember(account);
		
		return account.getId();
	}
	
	/**
	 * 修改会员密码
	 * @param dto 传入SessionDTO&lt;String,String&gt;对象
	 * <p>key=id:会员ID【必传】</p>
	 * <p>key=oldPasswd:原密码,明文【必传】</p>
	 * <p>key=newPasswd:新密码,明文【必传】</p>
	 * @throws BizException
	 */
	public void changeMemberPasswd(DTO<String,String> dto) {
		if(dto == null) {
			throw MemberException.PARAM_IS_NULL.newInstance("修改会员密码->to");
		}
		
		if(!dto.containsKey("id") || !dto.containsKey("oldPasswd") || !dto.containsKey("newPasswd")) {
			throw MemberException.PARAM_HINT_ERROR.newInstance("修改会员密码->id|oldPasswd|newPasswd");
		}
		
		Integer id = dto.getAsInteger("id");
		String oldPasswd = dto.getAsString("oldPasswd");
		String newPasswd = dto.getAsString("newPasswd");
		
		if(id == 0 || StringUtils.isEmpty(oldPasswd) || StringUtils.isEmpty(newPasswd)) {
			throw MemberException.PARAM_IS_NULL.newInstance("修改会员密码->id|oldPasswd|newPasswd");
		}
		
		DTO<String,String> accountDTO = new BaseDTO<String,String>();
		accountDTO.put("id", id.toString());
		AccountEntity account = memberDao.getMemberByPhoneOrId(accountDTO);
		if(account == null) {
			throw MemberException.PARAM_HINT_ERROR.newInstance("修改会员密码->用户不存在!id="+id);
		}
		if(!account.getPassword().equals(MD5Helper.encrypt(oldPasswd))) {
			throw MemberException.PARAM_HINT_ERROR.newInstance("修改会员密码->原密码输入不正确,请重新操作!");
		}
		DTO<String,String> passwdDTO = new BaseDTO<String,String>();
		passwdDTO.put("id", id.toString());
		passwdDTO.put("password", MD5Helper.encrypt(newPasswd));
		
		memberDao.changeMemberPasswd(passwdDTO);

		//记录业务日志
		String bef_Data = JSONObject.toJSONString(account);
		account.setPassword(MD5Helper.encrypt(newPasswd));
		String aft_data = JSONObject.toJSONString(account);

		bizLogForUpdate(PropertiesHelper.SYSTEM_ID_MEMBER,bef_Data,aft_data,dto.getUserId(),"修改会员密码");
		
	}

	/**
	 * 解除会员与指定微信号的绑定关系
	 * @param memberId 会员ID【必传】
	 * @param unionId 微信UNIONID【必传】
	 * @param aa01 操作人编号【必传】
	 * @throws BizException
	 */
	public void removeMemberWXBind(int memberId, String unionId,int aa01) {
		if(memberId == 0 || StringUtils.isEmpty(unionId)) {
			throw MemberException.PARAM_IS_NULL.newInstance("解除会员与微信绑定关系->memberId|unionId");
		}
		
		DTO<String,String> bindDTO = new BaseDTO<String,String>();
		bindDTO.put("aid", String.valueOf(memberId));
		bindDTO.put("unionid", unionId);
		
		MemberWXBindEntity wxBind = memberDao.getMemberWXBind(bindDTO);
		if(wxBind == null) {
			throw MemberException.PARAM_HINT_WARN.newInstance("解除会员微信绑定关系->该会员与当前微信未建立绑定关系");
		}
		
		memberDao.removeMemberWXBind(wxBind.getId());

		//记录业务日志
		bizLogForDelete(PropertiesHelper.SYSTEM_ID_MEMBER,JSONObject.toJSONString(wxBind),aa01,"解除会员与指定微信号的绑定关系");

	}
}
