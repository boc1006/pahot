package cn.pahot.upms.auth.entity;

import java.io.Serializable;

/**
 * 系统菜单实体
 * <p>@Title: MenuEntity.java 
 * <p>@Package cn.pahot.upms.auth.entity 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年11月21日 上午11:19:09 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class MenuEntity implements Serializable{

	private static final long serialVersionUID = 2587262691635616149L;
	private Integer id;
	private String sid;
	private Integer parentid;
	private String name;
	private String mtype;
	private String btype;
	private String icon;
	private String jump;
	private String uri;
	private String state;
	private String hashkey;
	private Integer sort;
	private String level;
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
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getJump() {
		return jump;
	}
	public void setJump(String jump) {
		this.jump = jump;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHashkey() {
		return hashkey;
	}
	public void setHashkey(String hashkey) {
		this.hashkey = hashkey;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
