package cn.pahot.upms.auth.entity;

import java.io.Serializable;

/**
 * 系统角色实体
 * <p>@Title: RoleEntity.java 
 * <p>@Package cn.pahot.upms.auth.entity 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 上午11:26:23 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class RoleEntity implements Serializable{

	private static final long serialVersionUID = 6267815968443798028L;
	private Integer id;
	private String sid;
	private String name;
	private Long validity;
	private String state;
	private Integer sort;
	private String arights;
	private String hrights;
	private Integer aa01;
	private Long aa02;
	private Integer ab01;
	private Long ab02;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getValidity() {
		return validity;
	}
	public void setValidity(Long validity) {
		this.validity = validity;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getArights() {
		return arights;
	}
	public void setArights(String arights) {
		this.arights = arights;
	}
	public String getHrights() {
		return hrights;
	}
	public void setHrights(String hrights) {
		this.hrights = hrights;
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
	public Integer getAb01() {
		return ab01;
	}
	public void setAb01(Integer ab01) {
		this.ab01 = ab01;
	}
	public Long getAb02() {
		return ab02;
	}
	public void setAb02(Long ab02) {
		this.ab02 = ab02;
	}
	
}
