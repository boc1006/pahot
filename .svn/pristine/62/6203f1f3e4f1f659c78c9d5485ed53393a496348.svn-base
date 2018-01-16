package cn.pahot.member.facade;

import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;

import cn.pahot.member.entity.AccountEntity;
import cn.pahot.member.entity.MemberWXBindEntity;

/**
 * 定义会员账号管理服务接口,包括会员登录、注册、授权等
 * <p>@Title: MemberFacade.java 
 * <p>@Package cn.pahot.member.facade 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月18日 上午11:48:27 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface MemberFacade {
	
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
	AccountEntity bizRegistryMember(String phone,boolean isDefPwd,String passwd,boolean isSendSms,String regType,String terminal,String name,String tel) throws BizException;
	
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
	void bindMemberWX(MemberWXBindEntity entity) throws BizException;
	
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
	int registryMember(String phone,String passwd,String name,String tel,String terminal) throws BizException;
	
	/**
	 * 修改会员密码
	 * @param dto 传入SessionDTO&lt;String,String&gt;对象
	 * <p>key=id:会员ID【必传】</p>
	 * <p>key=oldPasswd:原密码,明文【必传】</p>
	 * <p>key=newPasswd:新密码,明文【必传】</p>
	 * @throws BizException
	 */
	void changeMemberPasswd(DTO<String,String> dto) throws BizException;
	
	/**
	 * 解除会员与指定微信号的绑定关系
	 * @param memberId 会员ID【必传】
	 * @param unionId 微信UNIONID【必传】
	 * @param aa01操作人编号【必传】
	 * @throws BizException
	 */
	void removeMemberWXBind(int memberId,String unionId,int aa01) throws BizException;
}
