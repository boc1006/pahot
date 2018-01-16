package cn.pahot.xa.scheduler.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 重写一个动态数据源的类，继承Spring
 * AbstractRoutingDataSource类，并且实现determineCurrentLookupKey()方法。
 * 看determineCurrentLookupKey()方法的注释，意思大致就是决定当前数据源的key。
 * <p>@Title DynamicDataSource</p>
 * <p>@Description com.dgg.message.xa.utils</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年7月3日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDateSourceType();
	}

}
