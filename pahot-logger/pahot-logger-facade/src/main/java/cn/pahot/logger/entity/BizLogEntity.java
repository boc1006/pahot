package cn.pahot.logger.entity;

import java.io.Serializable;

/**
 * 业务操作日志实体
 * <p>@Title: BizLogEntity.java 
 * <p>@Package cn.pahot.logger.entity 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月15日 下午3:54:48 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class BizLogEntity implements Serializable {

	private static final long serialVersionUID = 6788005061202387739L;
	public transient final static String ADD = "01";
	public transient final static String UPDATE = "02";
	public transient final static String DELETE = "03";
	private Long id;
	private String sid;
	private String method_name;
	private String bef_data;
	private String aft_data;
	private String handler;
	private String remark;
	private Integer aa01;
	private Long aa02;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getMethod_name() {
		return method_name;
	}
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
	public String getBef_data() {
		return bef_data;
	}
	public void setBef_data(String bef_data) {
		this.bef_data = bef_data;
	}
	public String getAft_data() {
		return aft_data;
	}
	public void setAft_data(String aft_data) {
		this.aft_data = aft_data;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getAa01() {
		return aa01;
	}
	public void setAa01(Integer aa01) {
		this.aa01 = aa01;
	}
	public Long getAa02() {
		return aa02;
	}
	public void setAa02(Long aa02) {
		this.aa02 = aa02;
	}
	

}
