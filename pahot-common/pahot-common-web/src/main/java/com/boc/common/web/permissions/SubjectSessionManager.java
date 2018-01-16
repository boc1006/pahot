package com.boc.common.web.permissions;

/**
 * 会话数据持久化适配器
 * <p>@Title ISessionDataAdapter</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月14日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public abstract class SubjectSessionManager {

	/**
	 * 保存会话数据
	 * @param sessionId
	 * @param data
	 * @param second 秒
	 */
	public abstract void setSubjectSession(String sessionId,Subject subject,int second);

	/**
	 * 获取持久化数据
	 * @param sessionId
	 * @return
	 */
	public abstract Subject getSubjectSession(String sessionId);


	/**
	 * 更新持久化数据有效时间
	 * @param sessionId
	 * @param second 秒
	 */
	public abstract void setSubjectSessionExpire(String sessionId,int second);

	/**
	 * 删除持久化数据
	 * @param sessionId
	 */
	public abstract void delSubjectSession(String sessionId);

}
