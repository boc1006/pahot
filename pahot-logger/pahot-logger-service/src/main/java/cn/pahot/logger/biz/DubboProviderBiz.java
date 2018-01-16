package cn.pahot.logger.biz;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pahot.logger.dao.DubboProviderMapper;

/**
 * 服务接口测试结果业务逻辑实现
 * <p>@Title: DubboProviderBiz.java 
 * <p>@Package cn.pahot.logger.biz 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月19日 上午10:07:47 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
@Service("dubboProviderBiz")
public class DubboProviderBiz {
	
	@Autowired
	private DubboProviderMapper dubboProviderMapper;
	
	public void saveServiceInfo(Map<String, String> info) {
		int cnt = dubboProviderMapper.existsServiceInfo(info);
		if(cnt > 0) {
			dubboProviderMapper.updateServiceInfo(info);
		}else {
			dubboProviderMapper.saveServiceInfo(info);
		}
	}
}
