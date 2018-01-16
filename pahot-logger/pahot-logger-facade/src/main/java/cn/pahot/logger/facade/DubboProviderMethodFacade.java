package cn.pahot.logger.facade;

import java.util.Map;

/**
 * Dubbo 服务接口Junit测试操作
 * <p>@Title: DubboProviderMethodFacade.java 
 * <p>@Package cn.pahot.logger.facade 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月18日 下午5:56:50 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public interface DubboProviderMethodFacade {
    /**
     * 保存调用的Dubbo service method信息
     *
     * @param dto 入参
     *            <li> <b>system</b> 系统名称
     *            <li> <b>serverIp</b> 服务IP地址
     *            <li> <b>serverPort</b> dubbo暴露端口
     *            <li> <b>serverName</b> dubbo服务类名
     *            <li> <b>methodName</b> dubbo服务方法名称
     *            <li> <b>paramerercount</b> 方法参数个数
     *            <li> <b>paramerer</b> 入参
     *            <li> <b>state</b> 状态，0
     *            <li> <b>username</b> 开发人员姓名
     */
    void saveDubboProviderInfo(Map<String, String> dto);
}
