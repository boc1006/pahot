package cn.pahot.xa.scheduler.datasource;

/**
 * 多数据源DataSourceContextHolder
 * <p>@Title DataSourceContextHolder</p>
 * <p>@Description com.dgg.message.xa.utils</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年7月3日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class DataSourceContextHolder {
	
	/**
	 * 为了线程安全，设置每个线程单独的变量
	 */
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	/**
	 * 设置当前数据源的key
	 * @param dataSourceType
	 */
	public static void setDataSourseType(String dataSourceType){
		contextHolder.set(dataSourceType);
	}
	
	/**
	 * 获取当前数据源的key
	 * @return
	 */
	public static String getDateSourceType(){
		return contextHolder.get();
	}

	
	/**
	 * 清楚当前数据源的key
	 */
	public static void clearDataSourceType(){
		contextHolder.remove();
	}
	
	

}
