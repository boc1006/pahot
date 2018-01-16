package cn.pahot.message.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.utils.DateUtils;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import cn.pahot.message.biz.AliDyHelperOld;
import cn.pahot.message.consts.MessageConst;
import cn.pahot.message.consts.SmsChannelEnum;
import cn.pahot.message.consts.SmsStateEnum;
import cn.pahot.message.dao.sms.SmsMapper;
import cn.pahot.message.entity.SMSEntity;

/**
 * 定时执行短信发送
 * <p>@Title: SmsSendJob.java 
 * <p>@Package cn.pahot.message.scheduler 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月11日 下午1:35:21 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Component
public class SmsSendJob {
	private final static long ONE_MINUTE =  60 * 1000;
	private final static String STATE_IN = SmsStateEnum.SMS_STATE_WATTING.key+","+SmsStateEnum.SMS_STATE_RETRY.key;
	
	@Autowired
	private SmsMapper smsDao;
	
	@Scheduled(fixedRate=ONE_MINUTE)
    public void fixedDelaySms(){
		//待发送和重试发送
		DTO<String,String> param = new BaseDTO<String,String>();
		param.put("state", STATE_IN);
		param.put("tasktime", DateUtils.getCurrDateTime());
        List<SMSEntity> list = smsDao.querySmsTaskList(param);
//        System.out.println("待发送短信任务列表=====>"+list.size());
        for(SMSEntity sms :list) {
        	//构建一个发送任务
    		SmsChannelEnum channelEnum = SmsChannelEnum.toEnum(sms.getChannel());
    		
    		SMSEntity updSms = new SMSEntity();
    		updSms.setId(sms.getId());
    		updSms.setState(SmsStateEnum.SMS_STATE_SENDDING.key);
    		updSms.setSendtime(DateUtils.getCurrDateTimeToLong());
    		
    		switch(channelEnum) {
				case CHANNEL_ALIDY://阿里大鱼短信渠道
					AlibabaAliqinFcSmsNumSendResponse res = AliDyHelperOld.sendSms(sms.getTid(), sms.getPhones(), sms.getContents(), MessageConst.ALIDY_SIGNNAME);
					updSms.setReturetime(DateUtils.getCurrDateTimeToLong());
					updSms.setReturnmsg(JSONObject.toJSONString(res));
					if(res.isSuccess()) {//短信返回成功
						updSms.setReturnstate(res.getResult().getErrCode());
						updSms.setState(SmsStateEnum.SMS_STATE_SUCCESS.key);
					}else {
						updSms.setReturnstate(res.getErrorCode());
						updSms.setRetry(sms.getRetry() + 1);
						if(sms.getMaxretry() <= sms.getRetry() + 1) {
							updSms.setState(SmsStateEnum.SMS_STATE_FAIL.key);
						}else {
							updSms.setState(SmsStateEnum.SMS_STATE_RETRY.key);
						}
					}
					updSms.setReturncontent("");
					updSms.setModifytime(DateUtils.getCurrDateTimeToLong());
					smsDao.updSmsTask(updSms);
					break;
				default:
					updSms.setState(SmsStateEnum.SMS_STATE_FAIL.key);
					updSms.setReturnmsg("未知的短信渠道!");
					updSms.setModifytime(DateUtils.getCurrDateTimeToLong());
					smsDao.updSmsTask(updSms);
    		}
        }
    }

}
