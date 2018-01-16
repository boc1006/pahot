package cn.pahot.xa.scheduler.biz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.boc.common.enums.DataSourceEnums;
import com.boc.common.enums.PropertiesHelper;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.utils.DateUtils;

import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.entity.MessagePubEntity;
import cn.pahot.xa.enums.MessageLiveStateEnum;
import cn.pahot.xa.enums.MessageStateEnum;
import cn.pahot.xa.facade.CapXaFacade;
import cn.pahot.xa.scheduler.dao.ACKMapper;
import cn.pahot.xa.scheduler.datasource.DataSourceContextHolder;

/**
 * 分布式事务定时任务业务处理逻辑
 * <p>@Title CrontabBiz</p>
 * <p>@Description com.dgg.message.xa.notify.biz</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年7月4日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Service("crontabBiz")
public class CrontabBiz {
	private static final Log log = LogFactory.getLog(CrontabBiz.class);

	@Resource
	private CapXaFacade capXaFacade;
	
	@Resource
	private ACKMapper ackMapper;
	
	/**
	 * 处理[waiting_confirm]状态的消息
	 * 
	 * @param messages
	 */
	public void handleWaitingConfirmTimeOutMessages() {
		DTO<String,String> param = new BaseDTO<String,String>();
		// 获取配置的开始处理的时间
		String dateStr = getCreateTimeBefore();
		param.put("aa02", dateStr);// 取存放了多久的消息
		param.put("sortType", "ASC"); // 分页查询的排序方式，正向排序
		param.put("state", MessageStateEnum.CONFIRMING.key);// 取状态为“待确认”的消息
		param.put("ext2", "1");//获取所有点对点模式的消息
		
		List<MessageEntity> lstDTO = capXaFacade.getMessageList(param);
		if (StringUtils.isEmpty(lstDTO)) {
			log.debug("无可处理【waiting_confirm】的消息");
			return;
		}
		for (MessageEntity message : lstDTO) {
			try {
				if(StringUtils.isEmpty(message.getExt1())) {
					log.error("跳过处理[waiting_confirm]消息ID为[" + message.getId() + "]的消息,数据库标识为空!");
					continue;
				}
				DataSourceEnums dataSourceEnums = DataSourceEnums.toEnum(message.getExt1());//数据库名称
				DataSourceContextHolder.setDataSourseType(dataSourceEnums.key);
				long count = ackMapper.mainTransactionACK(message.getId());
				if(count > 0) {
					capXaFacade.confirmAndSendMessage(String.valueOf(message.getId()));
				}else{
					capXaFacade.deleteMessageByMessageId(String.valueOf(message.getId()));
				}
				log.debug("结束处理[waiting_confirm]消息ID为[" + message.getId() + "]的消息");
			} catch (Exception e) {
				log.error("处理[waiting_confirm]消息ID为[" + message.getId() + "]的消息异常：", e);
			}
		}
	}
	
	/**
	 * 处理[waiting_confirm]状态的订阅消息
	 * 
	 * @param messages
	 */
	public void handleWaitingConfirmTimeOutSubMessages() {
		// 获取配置的开始处理的时间
		String dateStr = getCreateTimeBefore();
		List<MessagePubEntity> lstDTO = capXaFacade.getPubMessageList(Long.parseLong(dateStr));
		if (StringUtils.isEmpty(lstDTO)) {
			log.debug("无可处理【waiting_confirm】的订阅消息");
			return;
		}
		for (MessagePubEntity dto : lstDTO) {
			try {
				DataSourceEnums dataSourceEnums = DataSourceEnums.toEnum(dto.getDbid());//数据库名称
				DataSourceContextHolder.setDataSourseType(dataSourceEnums.key);
//				boolean isAck = generalDao.mainTransactionACK(dto.getPid());
				long count = ackMapper.mainTransactionACK(dto.getPid());
				if(count > 0) {
					capXaFacade.confirmAndSendSubMessage(dto.getPid());
				}else{
					capXaFacade.deleteMessageByPubMessageId(dto.getPid());
				}
				log.debug("结束处理[waiting_confirm]消息ID为[" + dto.getPid() + "]的订阅消息");
			} catch (Exception e) {
				log.error("处理[waiting_confirm]消息ID为[" + dto.getPid() + "]的订阅消息异常：", e);
			}
		}
	}

	/**
	 * 处理[SENDING]状态并超时的消息
	 */
	public void handleSendingTimeOutMessage() {

		DTO<String,String> param = new BaseDTO<String,String>();
		// 获取配置的开始处理的时间
		String dateStr = getCreateTimeBefore();
		param.put("aa02", dateStr);// 取存放了多久的消息
		param.put("sortType", "ASC"); // 分页查询的排序方式，正向排序
		param.put("state", MessageStateEnum.SENDDING.key);// 取状态为发送中的消息
		param.put("isdead", MessageLiveStateEnum.ALIVED.key);// 取存活的发送中消息

		List<MessageEntity> lstDTO = capXaFacade.getMessageList(param);
		if (StringUtils.isEmpty(lstDTO)) {
			log.debug("无可处理【SENDING】的消息");
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		log.debug("开始处理[SENDING]状态的消息,总条数[" + lstDTO.size() + "]");

		// 根据配置获取通知间隔时间
		Map<Integer, Integer> notifyParam = getSendTime();

		for (MessageEntity message : lstDTO) {
			try {
				log.debug("开始处理[SENDING]消息ID为[" + message.getId() + "]的消息");
				// 判断发送次数
				int maxTimes = Integer.valueOf(PropertiesHelper.MESSAGE_MAX_SEND_TIMES);
				log.debug("[SENDING]消息ID为[" + message.getId() + "]的消息,已经重新发送的次数[" + message.getRepeatcount() + "]");

				// 如果超过最大发送次数直接退出
				if (maxTimes < message.getRepeatcount()) {
					// 标记为死亡
					capXaFacade.setMessageToAreadlyDead(String.valueOf(message.getId()));
					continue;
				}
				// 判断是否达到发送消息的时间间隔条件
				int reSendTimes = message.getRepeatcount();
				int times = notifyParam.get(reSendTimes == 0 ? 1 : reSendTimes);

				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MINUTE, -times);
				long needTime = Long.valueOf(DateUtils.formatDateTime.format(calendar.getTime()));

				long hasTime = 0;
				if (StringUtils.isEmpty(message.getAb02())) {
					hasTime = message.getAa02();
				} else {
					hasTime = message.getAb02();
				}
				// 判断是否达到了可以再次发送的时间条件
				if (hasTime > needTime) {
					log.debug("currentTime[" + sdf.format(new Date()) + "],[SENDING]消息上次发送时间[" + sdf.format(message.getAb02()) + "],必须过了[" + times + "]分钟才可以再发送。");
					continue;
				}

				// 重新发送消息
				capXaFacade.reSendMessage(message);

				log.debug("结束处理[SENDING]消息ID为[" + message.getId() + "]的消息");
			} catch (Exception e) {
				log.error("处理[SENDING]消息ID为[" + message.getId() + "]的消息异常：", e);
			}
		}

	}

	/**
	 * 处理状态为“已确认”并达到延迟发送时间的消息
	 */
	public void handleConfirmedAndDelaydMessage() {
		capXaFacade.sendConfirmedAndDelaydMessage();
	}

	/**
	 * 根据配置获取通知间隔时间
	 * 
	 * @return
	 */
	private Map<Integer, Integer> getSendTime() {
		Map<Integer, Integer> notifyParam = new HashMap<Integer, Integer>();
		notifyParam.put(1, Integer.valueOf(PropertiesHelper.MESSAGE_SEND_1_TIME));
		notifyParam.put(2, Integer.valueOf(PropertiesHelper.MESSAGE_SEND_2_TIME));
		notifyParam.put(3, Integer.valueOf(PropertiesHelper.MESSAGE_SEND_3_TIME));
		notifyParam.put(4, Integer.valueOf(PropertiesHelper.MESSAGE_SEND_4_TIME));
		notifyParam.put(5, Integer.valueOf(PropertiesHelper.MESSAGE_SEND_5_TIME));
		return notifyParam;
	}

	/**
	 * 获取配置的开始处理的时间
	 * 
	 * @return
	 */
	private String getCreateTimeBefore() {
		String duration = PropertiesHelper.MESSAGE_HANDLE_DURATION;
		long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
		Date date = new Date(currentTimeInMillis - Integer.valueOf(duration) * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(date);
		return dateStr;
	}

	
}
