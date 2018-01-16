package cn.pahot.message.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.boc.common.biz.IdWorker;
import com.boc.common.biz.IdWorkerUtil;
import com.boc.common.enums.PropertiesHelper;
import com.boc.common.exception.BizException;
import com.boc.common.utils.DateUtils;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import cn.pahot.message.consts.MessageConst;
import cn.pahot.message.consts.SmsChannelEnum;
import cn.pahot.message.consts.SmsStateEnum;
import cn.pahot.message.dao.sms.SmsMapper;
import cn.pahot.message.entity.SMSCodeEntity;
import cn.pahot.message.entity.SMSEntity;
import cn.pahot.message.entity.SMSIPList;
import cn.pahot.message.entity.SMSVercodeIP;
import cn.pahot.message.entity.SMSVercodeLog;
import cn.pahot.message.exception.SmsException;

/**
 * 短信发送业务逻辑处理
 * <p>@Title: SmsBiz.java 
 * <p>@Package cn.pahot.message.biz 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月8日 下午7:07:28 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("smsBiz")
public class SmsBiz {
	private int _validtime = Integer.parseInt(PropertiesHelper.SMS_VALIDTIME);
	private int _valTimes =Integer.parseInt(PropertiesHelper.SMS_VALIDTIME_TIMES);
	
	@Autowired
	private SmsMapper smsDao;
	
	/**
	 * 短信验证码发送接口
	 * @param templateId 模板编号
	 * @param channel 发送渠道->cn.pahot.message.consts.SmsChannelEnum 【必传】
	 * @param sid 系统编号 【必传】
	 * @param phone 手机号码 【必传】
	 * @param content 短信验证码内容,JSON格式,必需包含code字段【必传】
	 * @param validity 短信验证码有效期,单位秒【必传】
	 * @param otherParams 其它参数,JSON格式 【根据不同渠道接口要求自定义,选传】
	 * @param bizDesc 业务描述
	 * @param ip 客户端IP地址  【必传】
	 * @throws BizException
	 */
//	@Transactional(noRollbackFor=SmsException.class)
	public void sendSmsCode(String templateId,String channel, String sid, String phone, String content,int validity, String otherParams,String bizDesc, String ip) {
		if(StringUtils.isEmpty(templateId) || 
				StringUtils.isEmpty(channel) ||
				StringUtils.isEmpty(sid) ||
				StringUtils.isEmpty(phone) ||
				StringUtils.isEmpty(content) ||
				StringUtils.isEmpty(ip)) {
			throw SmsException.PARAM_IS_NULL.newInstance("sendSmsCode:templateId|channel|sid|phone|content|ip");
		}
		SmsChannelEnum channelEnum = SmsChannelEnum.toEnum(channel);
		
		if(channelEnum != SmsChannelEnum.CHANNEL_ALIDY) {
			throw SmsException.PARAM_HINT_ERROR.newInstance("未知的短信渠道!");
		}
		
		//保存短信验证码发送记录
		SMSVercodeLog vercodeLogEntity = new SMSVercodeLog();
		vercodeLogEntity.setId(new IdWorker(1, 1).nextId());
		vercodeLogEntity.setIpaddr(ip);
		vercodeLogEntity.setPhone(phone);
		vercodeLogEntity.setContent(content);
		vercodeLogEntity.setSendtime(DateUtils.getCurrOrNextMinuteTime(0));
		smsDao.saveSmsVercodeLog(vercodeLogEntity);
		
		Integer ip_idx = ip.hashCode();
		SMSIPList iplistEntity = smsDao.getIpListForIp(ip_idx);
		
		if (null == iplistEntity) {// 未获取到该IP的黑、白名单信息
			SMSVercodeIP ipVercodeEntity = smsDao.getIpVercodeForIp(ip_idx);
			if (null == ipVercodeEntity) {// 未获取到该IP的短信验证码有效信息
				ipVercodeEntity = new SMSVercodeIP();
				ipVercodeEntity.setId(IdWorkerUtil.getId());
				ipVercodeEntity.setIpaddr_idx(ip_idx);
				ipVercodeEntity.setIpaddr(ip);
				ipVercodeEntity.setTimes(1);
				ipVercodeEntity.setMaxtimes(_valTimes);
				ipVercodeEntity.setValidtime(DateUtils.getCurrOrNextMinuteTime(_validtime));
				ipVercodeEntity.setUpdtime(DateUtils.getCurrOrNextMinuteTime(0));
				smsDao.saveIpVercodeList(ipVercodeEntity);
				
				saveAndSendCodeSms(templateId,channelEnum,sid,phone,content,validity,otherParams,bizDesc,0,0,0);
			} else {
				int times = ipVercodeEntity.getTimes();//当前发送次数
				int maxTimes = ipVercodeEntity.getMaxtimes();//最大发送次数
				long validtime = ipVercodeEntity.getValidtime();//有效截止时间
				long currTime = DateUtils.getCurrOrNextMinuteTime(0);//当前时间
				
				if(validtime < currTime) {//已过期,重置策略
					ipVercodeEntity.setTimes(times+1);
					ipVercodeEntity.setMaxtimes(times+1+_valTimes);
					ipVercodeEntity.setValidtime(DateUtils.getCurrOrNextMinuteTime(_validtime));
					ipVercodeEntity.setUpdtime(DateUtils.getCurrOrNextMinuteTime(0));
					smsDao.updateIpVercodeList(ipVercodeEntity);

					saveAndSendCodeSms(templateId,channelEnum,sid,phone,content,validity,otherParams,bizDesc,0,0,0);
				}else{
					if(times < maxTimes) {
						ipVercodeEntity.setTimes(times+1);
						ipVercodeEntity.setUpdtime(DateUtils.getCurrOrNextMinuteTime(0));
						smsDao.updateIpVercodeList(ipVercodeEntity);

						saveAndSendCodeSms(templateId,channelEnum,sid,phone,content,validity,otherParams,bizDesc,0,0,0);
					}else{
						throw SmsException.PARAM_HINT_ERROR.newInstance("已达到最大发送次数");
					}
				}
			}
		} else {// 已获取到该IP的黑、白名单信息
			String worb = iplistEntity.getWorb();
			if("01".equals(worb)) {//白名单
				saveAndSendCodeSms(templateId,channelEnum,sid,phone,content,validity,otherParams,bizDesc,0,0,0);
			}else{
				throw SmsException.PARAM_HINT_ERROR.newInstance("你已被加入黑名单,禁止发送短信!");
			}
		}
		
	}
	
	/**
	 * 保存并发送短信验证码
	 * @param templateId
	 * @param channel
	 * @param sid
	 * @param phone
	 * @param content
	 * @param otherParams
	 * @param bizDesc
	 * @param maxRetry
	 * @param retry
	 * @param delay
	 */
	public void saveAndSendCodeSms(String templateId,SmsChannelEnum channel, String sid, String phone, String content,int validity, String otherParams,String bizDesc,int maxRetry,int retry,int delay) throws RuntimeException{
		
		SMSCodeEntity code = smsDao.queryCodeByPhone(phone);
		
		if(code == null) {
			code = new SMSCodeEntity();
			code.setId(IdWorkerUtil.getId());
			code.setPhone(phone);
			code.setCode(JSONObject.parseObject(content).getString("code"));
			code.setValidity(DateUtils.getCurrOrNextSecondTime(validity));
			code.setExtime(DateUtils.getCurrDateTimeToLong());
			smsDao.saveCode(code);
		}else {
			code = new SMSCodeEntity();
			code.setPhone(phone);
			code.setCode(JSONObject.parseObject(content).getString("code"));
			code.setValidity(DateUtils.getCurrOrNextSecondTime(validity));
			code.setExtime(DateUtils.getCurrDateTimeToLong());
			smsDao.updateCode(code);
		}
		saveAndSendSms(templateId, channel, sid, phone, content, otherParams, bizDesc, maxRetry, retry, delay);
	}
	
	/**
	 * 保存并发送短信
	 * @param templateId
	 * @param channel
	 * @param sid
	 * @param phone
	 * @param content
	 * @param otherParams
	 * @param bizDesc
	 * @param maxRetry
	 * @param retry
	 * @param delay
	 */
	public void saveAndSendSms(String templateId,SmsChannelEnum channel, String sid, String phone, String content, String otherParams,String bizDesc,int maxRetry,int retry,int delay) {
		
		//先保存发送任务
		Long smsId = IdWorkerUtil.getId();
		SMSEntity sms = new SMSEntity();
		sms.setId(smsId);
		sms.setChannel(channel.key);
		sms.setTid(templateId);
		sms.setSid(sid);
		sms.setPhones(phone);
		sms.setContents(content);
		sms.setOtherparams(otherParams);
		sms.setBusdesc(bizDesc);
		sms.setState(SmsStateEnum.SMS_STATE_WATTING.key);
		sms.setMaxretry(maxRetry);
		sms.setRetry(retry);
		sms.setBizinvoketime(DateUtils.getCurrDateTimeToLong());
		sms.setTasktime(DateUtils.getCurrOrNextMinuteTime(delay));
		smsDao.saveSmsTask(sms);
		
		if(delay > 0) return;//表示延迟发送
		
		//构建一个发送任务
		SMSEntity updSms = new SMSEntity();
		updSms.setId(smsId);
		updSms.setState(SmsStateEnum.SMS_STATE_SENDDING.key);
		updSms.setSendtime(DateUtils.getCurrDateTimeToLong());
		
		switch(channel) {
		case CHANNEL_ALIDY://阿里大鱼短信渠道
			AlibabaAliqinFcSmsNumSendResponse res = AliDyHelperOld.sendSms(templateId, phone, content, MessageConst.ALIDY_SIGNNAME);
			updSms.setReturetime(DateUtils.getCurrDateTimeToLong());
			updSms.setReturnmsg(JSONObject.toJSONString(res));
			if(res.isSuccess()) {//短信返回成功
				updSms.setReturnstate(res.getResult().getErrCode());
				updSms.setState(SmsStateEnum.SMS_STATE_SUCCESS.key);
			}else {
				updSms.setReturnstate(res.getErrorCode());
				if(maxRetry == retry) {
					updSms.setState(SmsStateEnum.SMS_STATE_FAIL.key);
				}else {
					updSms.setState(SmsStateEnum.SMS_STATE_RETRY.key);
				}
			}
			updSms.setReturncontent("");
			updSms.setModifytime(DateUtils.getCurrDateTimeToLong());
			smsDao.updSmsTask(updSms);
			break;
		default:throw SmsException.PARAM_HINT_ERROR.newInstance("未知的短信渠道!");
		}
	}
	
	/**
	 * 业务短信发送接口
	 * @param templateId 模板编号
	 * @param channel 发送渠道->cn.pahot.message.consts.SmsChannelEnum 【必传】
	 * @param sid 系统编号 【必传】
	 * @param phones 手机号码,最多支持1000个手机号码批量发送,多个手机号码用“,”分隔 【必传】
	 * @param content 短信内容
	 * @param otherParams 其它参数,JSON格式 【根据不同渠道接口要求自定义】
	 * @param bizDesc 业务描述
	 * @param maxRetry 发送失败最大重试次数,0表示不重试
	 * @param delay 延迟发送时间,单位为分钟,0表示立即发送
	 * @throws BizException
	 */
	public void sendBizSms(String templateId,String channel,String sid,String phones,String content,String otherParams,String bizDesc,int maxRetry,int delay) {
		if(StringUtils.isEmpty(templateId) || 
				StringUtils.isEmpty(channel) ||
				StringUtils.isEmpty(sid) ||
				StringUtils.isEmpty(phones) ||
				StringUtils.isEmpty(content)) {
			throw SmsException.PARAM_IS_NULL.newInstance("sendSmsCode:templateId|channel|sid|phones|content");
		}
		
		SmsChannelEnum channelEnum = SmsChannelEnum.toEnum(channel);
		
		if(channelEnum != SmsChannelEnum.CHANNEL_ALIDY) {
			throw SmsException.PARAM_HINT_ERROR.newInstance("未知的短信渠道!");
		}
		
		saveAndSendSms(templateId, channelEnum, sid, phones, content, otherParams, bizDesc, maxRetry, 0, delay);
	}

	/**
	 * 验证短信验证码
	 * @param phone 手机号码
	 * @param code 验证码
	 * @return
	 */
	public boolean verifyCode(String phone, String code) {
		SMSCodeEntity codeEntity = smsDao.queryCodeByPhone(phone);
		if(codeEntity == null) {
			return false;
		}
		
		if(codeEntity.getValidity() < DateUtils.getCurrDateTimeToLong()) {
			return false;
		}
		if(!codeEntity.getCode().equals(code)) {
			return false;
		}
		return true;
	}
}
