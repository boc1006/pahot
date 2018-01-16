package cn.pahot.xa.scheduler.crontab;

/**
 * 分布式事务恢复定时任务
 * <p>@Title XaCrontab</p>
 * <p>@Description com.dgg.message.xa.crontab</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年7月3日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public interface XaCrontab {
	/**
	 * 处理状态为“待确认”但已超时的消息.
	 */
	public void handleWaitingConfirmTimeOutMessages();
	
	/**
	 * 处理状态为“待确认”但已超时的订阅消息.
	 */
	public void handleWaitingConfirmTimeOutSubMessages();
	
	/**
	 * 处理状态为“发送中”但超时没有被成功消费确认的消息
	 */
	public void handleSendingTimeOutMessage();
	
	/**
	 * 处理状态为“已确认”并达到延迟发送时间的消息
	 */
	public void handleConfirmedAndDelaydMessage();
}
