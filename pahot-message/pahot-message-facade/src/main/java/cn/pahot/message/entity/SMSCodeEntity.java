package cn.pahot.message.entity;

import java.io.Serializable;

/**
 * 短信验证码实体
 * <p>@Title: SMSCodeEntity.java 
 * <p>@Package cn.pahot.message.entity 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月20日 下午6:50:39 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class SMSCodeEntity implements Serializable {

	private static final long serialVersionUID = -5733767232703445679L;
	private Long id;
	private String phone;
	private String code;
	private Long validity;
	private Long extime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getValidity() {
		return validity;
	}
	public void setValidity(Long validity) {
		this.validity = validity;
	}
	public Long getExtime() {
		return extime;
	}
	public void setExtime(Long extime) {
		this.extime = extime;
	}

}
