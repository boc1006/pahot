package cn.pahot.message.facade;

import com.boc.common.exception.BizException;

/**
 * 短信发送服务接口
 * <p>@Title: SmsFacade.java 
 * <p>@Package cn.pahot.message.facade 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月8日 下午6:41:29 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface SmsFacade {
	
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
	void sendSmsCode(String templateId,String channel,String sid,String phone,String content,int validity,String otherParams,String bizDesc,String ip) throws BizException;
	
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
	void sendBizSms(String templateId,String channel,String sid,String phones,String content,String otherParams,String bizDesc,int maxRetry,int delay) throws BizException;
	
	/**
	 * 验证短信验证码
	 * @param phone 手机号码
	 * @param code 验证码
	 * @return
	 */
	boolean verifyCode(String phone,String code) throws BizException;
}
