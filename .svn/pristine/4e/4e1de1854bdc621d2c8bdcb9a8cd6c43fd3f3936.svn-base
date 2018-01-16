package com.boc.common.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boc.common.metatype.DTO;
import com.github.pagehelper.PageInfo;

/**
 * 客户端响应
 * @author user
 *
 */
public class WebResponse extends HashMap<String,Object> {

	private static final long serialVersionUID = -7266609079324764152L;

	/**
	 * 添加一个Object（可以是任意集合对象）结果集
	 * @param key
	 * @param value
	 */
	public void addValue(String key,Object value){
		this.put(key, value);
	}

	/**
	 * 添加一个Map对应
	 * @param map
	 */
	public void addValue(Map<String,Object> map){
		if(map != null)
			this.putAll(map);
	}

	/**
	 * 添加一个Dto对应
	 * @param map
	 */
	public void addDto(DTO<String,Object> dto){
		if(dto != null)
			this.putAll(dto);
	}

	/**
	 * 添加一个字符串结果集
	 * @param key
	 * @param value
	 */
	public void addValue(String key,String value){
		this.put(key, value);
	}

	/**
	 *
	 * @param value
	 */
	public void addResult(List<?> value) {
		addValue(Constants.DEFAULT_RESULT_KEY,value);
	}

	/**
	 * 设置请求返回状态
	 * @param code
	 */
	public void setAppCode(String code) {
		this.put(Constants.APPCODE, code);
	}

	/**
	 * 设置请求返回状态
	 * @param code
	 * @param msg
	 */
	public void setAppCode(String code,String msg) {
		this.put(Constants.APPCODE, code);
		this.put(Constants.APPMSG, msg);
	}

	/**
	 * 统一设置Ajax请求的返回信息
	 * @param status 请求状态，请求成功返回true，否则返回false
	 * @param Info 请求返回的提示消息
	 * @param url 请求返回的url地址
	 * @param data 请求返回的数据信息
	 */
	public void setAjaxMsg(boolean status,String Info,String url,Serializable data) {
		this.put(Constants.STATUS,status);
		this.put(Constants.INFO, Info);
		this.put(Constants.URL, url);
		this.put(Constants.DATA, data);
	}

	/**
	 * 统一设置Ajax请求的返回信息-重载
	 * @param status 请求状态，请求成功返回true，否则返回false
	 * @param Info 请求返回的提示消息
	 * @param url 请求返回的url地址
	 * @param data 请求返回的数据信息
	 */
	public void setAjaxMsg(boolean status,String Info,String url,Object data) {
		this.put(Constants.STATUS,status);
		this.put(Constants.INFO, Info);
		this.put(Constants.URL, url);
		this.put(Constants.DATA, data);
	}

	/**
	 * 统一设置datagrid请求返回的数据信息
	 * @param status 请求状态，请求成功返回true，否则返回false
	 * @param Info 请求返回的提示消息
	 * @param rows 请求返回的数据信息，以jsonArray字符串的形式返回
	 * @param total 请求到数据的总记录数
	 */
	public void setDataGridMsg(boolean status,String Info,List<?> rows,Long total) {
		this.put(Constants.STATUS,status);
		this.put(Constants.INFO, Info);
		this.put(Constants.ROWS, rows);
		this.put(Constants.TOTAL, total);
	}

	/**
	 * 设置分页参数
	 * @param page
	 */
	private void setPageValue(PageInfo<?> pageInfo) {
		this.put("endRow", pageInfo.getEndRow());
		this.put("navigateFirstPage",pageInfo.getNavigateFirstPage());
		this.put("navigateLastPage",pageInfo.getNavigateLastPage());
		this.put("navigatepageNums",pageInfo.getNavigatepageNums());
		this.put("navigatePages",pageInfo.getNavigatePages());
		this.put("prePage",pageInfo.getPrePage());
		this.put("nextPage",pageInfo.getNextPage());
		this.put("pageNum",pageInfo.getPageNum());
		this.put("pages",pageInfo.getPages());
		this.put("pageSize",pageInfo.getPageSize());
		this.put("size",pageInfo.getSize());
		this.put("startRow",pageInfo.getStartRow());
		this.put("total",pageInfo.getTotal());
	}

	public void setPageResult(String resultKey,PageInfo<?> pageInfo) {
		setAppCode(Constants.SUCCESS);
		this.put(resultKey, pageInfo.getList());
		this.setPageValue(pageInfo);
	}

	public void setPageResult(PageInfo<?> pageInfo) {
		setAppCode(Constants.SUCCESS);
		this.put(Constants.DEFAULT_RESULT_KEY, pageInfo.getList());
		this.setPageValue(pageInfo);
	}
}
