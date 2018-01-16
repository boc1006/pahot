package com.boc.common.web.permissions.bean;

import java.io.Serializable;

/**
 * 用户已授权系统信息
 * <p>@Title SysBean</p>
 * <p>@Description com.boc.common.web.permissions.bean</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2016年11月29日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class SysBean implements Serializable {
	private static final long serialVersionUID = -3649309901671779807L;
	private String id;//系统编号
    private String name; //系统名称
    private String type; //系统类型,参考字典'0001'
    private String icon; //系统图标
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
    
}
