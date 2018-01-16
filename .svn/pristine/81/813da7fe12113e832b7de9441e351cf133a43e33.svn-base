package com.boc.common.web.permissions;

import org.springframework.beans.factory.annotation.Autowired;

import com.boc.common.utils.cache.redis.SerializeUtils;
import com.boc.common.utils.cache.redis.springimpl.RedisClientTemplate;

/**
 * 通过REDIS保存SESSION数据
 * <p>@Title RedisSessionData</p>
 * <p>@Description com.boc.common.web.permissions</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月14日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class RedisSubjectSessionManager extends SubjectSessionManager {


	@Autowired(required=false)
	private RedisClientTemplate redisClientTemplate;

	@Override
	public void setSubjectSession(String sessionId, Subject subject,int second) {
		byte serializeSessionId[] = SerializeUtils.serialize(sessionId);
		redisClientTemplate.set(serializeSessionId, SerializeUtils.serialize(subject));
		redisClientTemplate.expire(serializeSessionId, second);
	}

	@Override
	public Subject getSubjectSession(String sessionId) {
		byte [] subjectByte = redisClientTemplate.get(SerializeUtils.serialize(sessionId));
		if(null == subjectByte) {
			return null;
		}
		return (Subject) SerializeUtils.deserialize(subjectByte);
	}

	@Override
	public void delSubjectSession(String sessionId) {
		redisClientTemplate.del(SerializeUtils.serialize(sessionId));
	}

	@Override
	public void setSubjectSessionExpire(String sessionId,int second) {
		redisClientTemplate.expire(SerializeUtils.serialize(sessionId), second);
	}

}
