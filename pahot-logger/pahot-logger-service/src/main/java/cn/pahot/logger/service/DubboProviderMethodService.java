package cn.pahot.logger.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pahot.logger.biz.DubboProviderBiz;
import cn.pahot.logger.facade.DubboProviderMethodFacade;

/**
 * Dubbo 服务接口Junit测试操作
 * <p>@Title: DubboProviderMethodService.java 
 * <p>@Package cn.pahot.logger.service 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月18日 下午5:59:21 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */

@Service("dubboProviderMethodFacade")
public class DubboProviderMethodService implements DubboProviderMethodFacade {
	
	@Autowired
	private DubboProviderBiz dubboProviderBiz;

	@Override
	public void saveDubboProviderInfo(Map<String, String> dto) {
		
		dubboProviderBiz.saveServiceInfo(dto);
	}

}
