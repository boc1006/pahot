package com.boc.common.metatype;

import java.io.Serializable;

import com.boc.common.utils.PojoUtils;

/**
 * 基础实体类
 * <p>@Title: BaseEntity.java 
 * <p>@Package com.boc.common.metatype 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月14日 上午11:37:20 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8852359198496289466L;
	private Integer aa01;
	private Long aa02;
	private Integer ab01;
	private Long ab02;
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
	
	@SuppressWarnings("unchecked")
	public <K,V> DTO<K,V> parseDTO() {
		return PojoUtils.realize(this, DTO.class);
	}
}
