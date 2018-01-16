package cn.pahot.member.entity;

import java.io.Serializable;

/**
 * 会员账号和微信账号绑定关系实体
 * <p>@Title: MemberWXBindEntity.java 
 * <p>@Package cn.pahot.member.entity 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月21日 上午11:08:57 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class MemberWXBindEntity implements Serializable {

	private static final long serialVersionUID = -4154008902152588123L;
	private Long id;
	private Integer aid;
	private String openid;
	private String unionid;
	private String nickname;
	private String sex;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private String privilegr;
	private Long bindtime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getPrivilegr() {
		return privilegr;
	}
	public void setPrivilegr(String privilegr) {
		this.privilegr = privilegr;
	}
	public Long getBindtime() {
		return bindtime;
	}
	public void setBindtime(Long bindtime) {
		this.bindtime = bindtime;
	}
	
}
