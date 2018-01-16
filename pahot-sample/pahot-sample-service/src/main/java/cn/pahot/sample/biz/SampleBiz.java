package cn.pahot.sample.biz;

import cn.pahot.sample.constant.SampleConst;
import cn.pahot.sample.dao.SampleMapper;
import cn.pahot.sample.entity.UserEntity;
import cn.pahot.sample.exception.SampleException;
import com.alibaba.fastjson.JSONObject;
import com.boc.common.core.biz.*;
import com.boc.common.core.lock.CuratorLock;
import com.boc.common.enums.PropertiesHelper;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.page.PageParams;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 业务处理
 * <p>@Title: SampleBiz.java 
 * <p>@Package cn.pahot.sample.biz 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月1日 下午1:14:05 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("sampleBiz")
public class SampleBiz extends BaseBiz {

	@Autowired
	private SampleMapper sampleDao;

	/**
	 * 记录业务日志
	 */
	public void bizLogger(int aa01) {
		bizLogForUpdate(PropertiesHelper.SYSTEM_ID_UPMS,"{}","{}",aa01,"修改商品基本信息");
	}
	
	public void addUser(UserEntity ue) {
		if(StringUtils.isEmpty(ue)) {
			throw SampleException.PARAM_IS_NULL.newInstance("用户信息");
		}
		//TODO 实现具体的业务逻辑
		sampleDao.addUser(ue);
	}

	public void updUser(UserEntity ue) {
		if(StringUtils.isEmpty(ue)) {
			throw SampleException.PARAM_IS_NULL.newInstance("用户信息");
		}
		
		if(ue.getId()==0) {
			throw SampleException.PARAM_IS_NULL.newInstance("用户ID");
		}
		//TODO 实现具体的业务逻辑
		sampleDao.updUser(ue);
	}

	public void delUser(Long userid) {
		if(userid==0) {
			throw SampleException.PARAM_IS_NULL.newInstance("用户ID");
		}
		//TODO 实现具体的业务逻辑
	}

	@MethodParamsVerify
	public void doBussiness(DTO<String,String> param,String t1,@Ignore Integer age,int i,UserEntity ue) {
		if(StringUtils.isEmpty(param)) {
			throw SampleException.PARAM_IS_NULL.newInstance("param");
		}
		//TODO 实现具体的业务逻辑
		String p1 = param.get("k1");
		String p2 = param.get("k2");
		String p3 = param.get("k3");
	}

	public void doBussinessForParam(Integer id, String state) {
		if(id == 0 || StringUtils.isEmpty(state)) {
			throw SampleException.PARAM_IS_NULL.newInstance("id|state");
		}
		//TODO 实现具体的业务逻辑
	}

	/**
	 * 分布式事务业务必须处理于个事务内
	 * @param id
	 * @param state
	 * @throws BizException
	 */
	@Transactional(rollbackFor = Exception.class)
	public long xaP2PBussiness(Integer id, String state,int aa01) {
		if(id == 0 || StringUtils.isEmpty(state) || aa01==0) {
			throw SampleException.PARAM_IS_NULL.newInstance("id|state|aa01");
		}

		UserEntity ue = new UserEntity();//这里构建一个消息体,在消息消费端获取该值进行具体业务处理
		ue.setName("13488888888");

		return distributedTransactional(PropertiesHelper.SAMPLE_QUEUENAME_DEMO,
				PropertiesHelper.SYSTEM_ID_UPMS,
				JSONObject.toJSONString(ue),aa01,"对业务进行描述",()->{
					//TODO 实现自己的业务逻辑

				});

	}

	/**
	 * 实现一个可订阅的分布式事务,业务处理必须在一个事务内
	 * @param id
	 * @param state
	 * @throws BizException
	 */
	@Transactional(rollbackFor = Exception.class)
	public long xaSubBussiness(Integer id, String state,int aa01) {
		if(id == null || id == 0 || StringUtils.isEmpty(state) || aa01==0) {
			throw SampleException.PARAM_IS_NULL.newInstance("id|state|aa01");
		}

		UserEntity ue = new UserEntity();//这里构建一个消息体,在消息消费端获取该值进行具体业务处理
		ue.setPhone("13458585987");

		return distributedTransactional(SampleConst.SAMPLE_SUBID,JSONObject.toJSONString(ue),
				aa01,"对业务进行描述",()->{
					//TODO 实现自己的业务逻辑
		});
	}

	/**
	 * 分布式锁实现例子
	 * 所有进入该业务方法的请求,在上一个业务处理未完成之前,所有的请求进行排队等待,直接上一个业务释放该锁
	 * @param id
	 * @param state
	 * @throws BizException
	 */
	@CuratorLock(id=SampleConst.LOCK_NO_SAMPLE_1001)
	public void dstributedLocks(Integer id, String state) {
		if(id == 0 || StringUtils.isEmpty(state)) {
			throw SampleException.PARAM_IS_NULL.newInstance("id|state");
		}
		
		//TODO 实现自己的业务逻辑
	}

//	@RedisLock(async = true,tryTime = 200,lock = RedisLockEnums.REENTRANT,lockName = "testLock",unit = TimeUnit.SECONDS,timeout = 10)
	@CuratorLock(id = 100)
	public void dstributedRedisLock(){
		try{
			//TimeUnit.SECONDS.sleep(1);
			Thread.sleep(1000);
			System.out.println("Thread name==>"+Thread.currentThread().getName());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UserEntity> queryUserList(String username) {
		return sampleDao.findByUserList();
	}

	/**
	 * 分页处理
	 * @param params
	 * @return
	 * @throws BizException
	 */
	public PageInfo<UserEntity> queryUserListPages(PageParams<String> params) {
		
		PageInfo<UserEntity> pi = DefaultPageBuilder.build(params, 
				new AbstractPageBuilder<UserEntity,String>() {
			
			@Override
			public Page<UserEntity> build(String param) {
				//TODO 业务逻辑处理
				
				return sampleDao.findByUserListPage();
			}
		});
		return pi;
	}

}
