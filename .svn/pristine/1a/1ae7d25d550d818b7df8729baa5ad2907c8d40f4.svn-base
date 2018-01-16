package cn.pahot.sample.biz;

import cn.pahot.sample.dao.SampleMapper;
import cn.pahot.sample.entity.TabEntity;
import com.boc.common.core.biz.BaseBiz;
import com.boc.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Spring 事务传播和隔离级别测试
 * <p>@Title pahot2.0
 * <p>@Author boc
 * <p>@Date 2017/12/25
 * <p>@Email 87080234@gmail.com
 * <p>@Department XXX部门
 * <p>@Version V1.0
 * <p>@Copyright © boc Inc. All rights reserved.
 */
@Service("transactionalBiz")
public class TransactionalBiz extends BaseBiz {
	@Autowired
	private SampleMapper sampleMapper;

	private AtomicInteger idx = new AtomicInteger(1);

	@Transactional(rollbackFor = Exception.class)
	public void transactional() {
		TabEntity tab = new TabEntity();
		tab.setName("t1:"+idx.getAndIncrement());

		sampleMapper.insertA(tab);
		methodB();
		methodC();
	}

	public void methodB(){
		TabEntity tab = new TabEntity();
		tab.setName("t2:"+idx.getAndIncrement());
		sampleMapper.insertB(tab);
	}

	public void methodC(){
		TabEntity tab = new TabEntity();
		tab.setName("t3:"+idx.getAndIncrement());
		sampleMapper.insertC(tab);
//		throw BizException.INSTANCE.newInstance("MethodC");
	}
}
