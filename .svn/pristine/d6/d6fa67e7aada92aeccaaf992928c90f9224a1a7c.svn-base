package cn.pahot.logger.dao;

import java.util.Map;

/**
 * Dubbo 服务接口Junit测试操作
 * <p>@Title: DubboProviderMapper.java 
 * <p>@Package cn.pahot.logger.dao 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月18日 下午6:01:53 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface DubboProviderMapper {
	
	/**
     * 保存调用的Dubbo service method信息
     *
     * @param info 入参
     *             <li> <b>system</b> 系统名称
     *             <li> <b>serverIp</b> 服务IP地址
     *             <li> <b>serverPort</b> dubbo暴露端口
     *             <li> <b>serverName</b> dubbo服务类名
     *             <li> <b>methodName</b> dubbo服务方法名称
     *             <li> <b>paramerercount</b> 方法参数个数
     *             <li> <b>paramerer</b> 入参
     *             <li> <b>state</b> 状态，0
     *             <li> <b>username</b> 开发人员姓名
     * @return 返回成功与否
     */
    void saveServiceInfo(Map<String, String> info);
    
    /**
     * 判断接口是否存在
     * @param info
     * @return
     */
    int existsServiceInfo(Map<String, String> info);
    
    /**
     * 新增接口信息
     * @param info
     */
    void updateServiceInfo(Map<String, String> info);
}
