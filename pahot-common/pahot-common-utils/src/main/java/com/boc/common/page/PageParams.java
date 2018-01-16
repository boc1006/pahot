package com.boc.common.page;

import java.io.Serializable;

/**
 * 分页组件-传入参数
 * <p>@Title: PageParams.java 
 * <p>@Package com.boc.common.page 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月1日 上午11:29:30 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class PageParams<T> implements Serializable{

	private static final long serialVersionUID = -3768623622345709161L;
	
	public transient static final String PAGENO_KEY = "pageNo";
	public transient static final String PAGESIZE_KEY = "pageSize";
	
	private int pageNo;
	private int pageSize;
	private T params;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public T getParams() {
		return params;
	}
	public void setParams(T params) {
		this.params = params;
	}
	
}
