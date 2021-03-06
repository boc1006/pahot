package cn.pahot.upms.auth.entity;

import java.io.Serializable;

/**
 * 系统用户实体
 * <p>@Title: UserEntity.java 
 * <p>@Package cn.pahot.upms.auth.entity 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 上午11:07:05 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 307890524214573975L;
	private Integer id;
	private String username;
	private String passwd;
	private Integer orgid;
	private String name;
	private String state;
	private String birthday;
	private String remark;
	private String sex;
	private String phone;
	private Integer aa01;
	private Long aa02;
	private Integer ab01;
	private Long ab02;
	private String orgstate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Integer getOrgid() {
		return orgid;
	}
	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getOrgstate() {
		return orgstate;
	}
	public void setOrgstate(String orgstate) {
		this.orgstate = orgstate;
	}
	
}
