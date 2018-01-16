package cn.pahot.member.entity;

import java.io.Serializable;

/**
 * 会员登录账号实体
 * <p>@Title: AccountEntity.java 
 * <p>@Package cn.pahot.member.entity 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月21日 上午11:27:57 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class AccountEntity implements Serializable{

	private static final long serialVersionUID = -2074279839188896299L;
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String tel;
	private Long regtime;
	private String type;
	private String terminal;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Long getRegtime() {
		return regtime;
	}
	public void setRegtime(Long regtime) {
		this.regtime = regtime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
}
