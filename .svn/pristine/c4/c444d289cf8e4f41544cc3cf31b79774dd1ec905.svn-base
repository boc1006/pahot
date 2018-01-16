package cn.pahot.message.service.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boc.common.exception.BizException;

import cn.pahot.message.biz.SmsBiz;
import cn.pahot.message.facade.SmsFacade;

/**
 * 短信发送服务实现
 * <p>@Title: SmsService.java 
 * <p>@Package cn.pahot.message.service.sms 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月8日 下午7:04:41 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("smsFacade")
public class SmsService implements SmsFacade{
	
	@Autowired
	private SmsBiz smsBiz;

	@Override
	public void sendSmsCode(String templateId,String channel, String sid, String phone, String content,int validity, String otherParams,String bizDesc, String ip)
			throws BizException {
		smsBiz.sendSmsCode(templateId,channel, sid, phone, content,validity, otherParams,bizDesc, ip);
	}

	@Override
	public void sendBizSms(String templateId,String channel, String sid, String phones, String content, String otherParams,
			String bizDesc, int maxRetry, int delay) throws BizException {
		smsBiz.sendBizSms(templateId,channel, sid, phones, content, otherParams, bizDesc, maxRetry, delay);
	}

	@Override
	public boolean verifyCode(String phone, String code) {
		
		return smsBiz.verifyCode(phone, code);
	}

}
