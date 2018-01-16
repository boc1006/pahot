package cn.pahot.sample.service;

import java.util.List;

import cn.pahot.sample.biz.TransactionalBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boc.common.core.test.annotation.DubboProviderMethod;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.PageInfo;

import cn.pahot.sample.biz.SampleBiz;
import cn.pahot.sample.entity.UserEntity;
import cn.pahot.sample.facade.SampleFacade;
import cn.pahot.xa.facade.CapXaFacade;

/**
 * Dubbo服务接口实现类
 * <p>@Title: SampleService.java 
 * <p>@Package cn.pahot.sample.service 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月1日 上午11:50:15 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */

@DubboProviderMethod(ip="192.168.3.113",name="黄杰",system="事例系统",systemNum="999999")
@Service("sampleFacade")
public class SampleService implements SampleFacade {

	@Autowired
	private CapXaFacade capXaFacade;
	
	@Autowired
	private SampleBiz sampleBiz;

	@Autowired
	TransactionalBiz transactionalBiz;

	@Override
	public void addUser(UserEntity ue) throws BizException {
		sampleBiz.addUser(ue);
	}

	@Override
	public void updUser(UserEntity ue) throws BizException {
		sampleBiz.updUser(ue);
	}

	@Override
	public void delUser(Long userid) throws BizException {
		sampleBiz.delUser(userid);
	}

	@Override
	public void doBussiness(DTO<String,String> param) throws BizException {
		sampleBiz.doBussiness(param,"123",Integer.valueOf(2),20,null);
	}

	@Override
	public void doBussinessForParam(Integer id, String state) throws BizException {
		sampleBiz.doBussinessForParam(id, state);
	}

	@Override
	public void xaP2PBussiness(Integer id, String state,int aa01) throws BizException {
		long messageId = sampleBiz.xaP2PBussiness(id, state, aa01);
		capXaFacade.confirmAndSendMessage(String.valueOf(messageId));
	}

	@Override
	public void xaSubBussiness(Integer id, String state,int aa01) throws BizException {
		long messageId = sampleBiz.xaSubBussiness(id, state, aa01);
		capXaFacade.confirmAndSendSubMessage(messageId);
	}

	@Override
	public void dstributedLocks(Integer id, String state) throws BizException {
		sampleBiz.dstributedLocks(id, state);
	}

	@Override
	public void dstributedRedisLock() throws BizException {
		sampleBiz.dstributedRedisLock();
	}

	@Override
	public List<UserEntity> queryUserList(String username) throws BizException {
		return sampleBiz.queryUserList(username);
	}

	@Override
	public PageInfo<UserEntity> queryUserListPages(PageParams<String> params) throws BizException {
		return sampleBiz.queryUserListPages(params);
	}

	@Override
	public void transactional() throws BizException {
		transactionalBiz.transactional();
	}

}
