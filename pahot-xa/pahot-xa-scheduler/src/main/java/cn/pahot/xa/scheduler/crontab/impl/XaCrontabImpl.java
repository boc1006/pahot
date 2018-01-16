package cn.pahot.xa.scheduler.crontab.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.pahot.xa.scheduler.biz.CrontabBiz;
import cn.pahot.xa.scheduler.crontab.XaCrontab;

/**
 * @author hj87080234@gmail.com
 * @date 2016年9月14日
 */
@Service("xaCrontab")
public class XaCrontabImpl implements XaCrontab{
	
	@Resource
	private CrontabBiz crontabBiz;

	@Override
	public void handleSendingTimeOutMessage() {
		crontabBiz.handleSendingTimeOutMessage();
	}

	@Override
	public void handleWaitingConfirmTimeOutMessages() {
		crontabBiz.handleWaitingConfirmTimeOutMessages();
	}

	@Override
	public void handleWaitingConfirmTimeOutSubMessages() {
		crontabBiz.handleWaitingConfirmTimeOutSubMessages();
	}

	@Override
	public void handleConfirmedAndDelaydMessage() {
		crontabBiz.handleConfirmedAndDelaydMessage();
	}

}
