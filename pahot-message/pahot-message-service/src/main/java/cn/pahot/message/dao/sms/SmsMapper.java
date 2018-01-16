package cn.pahot.message.dao.sms;

import java.util.List;

import com.boc.common.metatype.DTO;

import cn.pahot.message.entity.SMSCodeEntity;
import cn.pahot.message.entity.SMSEntity;
import cn.pahot.message.entity.SMSIPList;
import cn.pahot.message.entity.SMSVercodeIP;
import cn.pahot.message.entity.SMSVercodeLog;

/**
 * 短信发送DAO
 * <p>@Title: SmsMapper.java 
 * <p>@Package cn.pahot.message.dao.sms 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月11日 上午9:48:52 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface SmsMapper {
	
	/**
	 * 保存短信发送任务
	 * @param sms
	 */
	void saveSmsTask(SMSEntity sms);
	
	/**
	 * 修改短信发送任务
	 * @param sms
	 */
	void updSmsTask(SMSEntity sms);
	
	/**
	 * 根据任务状态查询任务列表
	 * @param state
	 * @return
	 */
	List<SMSEntity> querySmsTaskList(DTO<String,String> dto);
	
	/**
	 * 根据IP获取短信发送IP白/黑名单
	 * @param param
	 * @return
	 */
	SMSIPList getIpListForIp(Integer ip_idx);
	
	/**
	 * 保存短信发送IP白/黑名单
	 * @param param
	 */
	void saveIpList(SMSIPList param);
	
	/**
	 * 修改短信发送IP白/黑名单
	 * @param param
	 */
	void updateIpList(SMSIPList param);
	
	/**
	 * 根据IP地址查询短信验证码IP有效性
	 * @param ip_idx
	 * @return
	 */
	SMSVercodeIP getIpVercodeForIp(Integer ip_idx);
	
	void saveIpVercodeList(SMSVercodeIP param);
	
	void updateIpVercodeList(SMSVercodeIP param);
	
	void saveSmsVercodeLog(SMSVercodeLog param);
	
	SMSCodeEntity queryCodeByPhone(String phone);
	
	void saveCode(SMSCodeEntity entity);
	
	void updateCode(SMSCodeEntity entity);
}
