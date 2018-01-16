package cn.pahot.xa.entity;

import java.io.Serializable;

/**
 * 分布式消息订阅实体
 * <p>@Title CapXaBiz</p>
 * <p>@Description com.dgg.message.xa.biz</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年2月16日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class MessageSubEntity implements Serializable{

	private static final long serialVersionUID = -8642945041796740472L;
	private int id;//订阅编号
	private String subname;//订阅名称
	private String type;//订阅类型
	private String source;//消息来源
	private String queuename;//队列名称
	private String delays ="1000";//消息队列延迟时间
	private String state;//状态
	private String dbid;//数据源ID
	private String remark;//备注
	private String aa01;//操作人ID
	private String aa11;//操作人姓名
	private Long aa02;//操作时间
	private String ab01;//修改人ID
	private String ab11;//修改人姓名
	private Long ab02;//修改时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getQueuename() {
		return queuename;
	}
	public void setQueuename(String queuename) {
		this.queuename = queuename;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAa01() {
		return aa01;
	}
	public void setAa01(String aa01) {
		this.aa01 = aa01;
	}
	public String getAa11() {
		return aa11;
	}
	public void setAa11(String aa11) {
		this.aa11 = aa11;
	}
	public Long getAa02() {
		return aa02;
	}
	public void setAa02(Long aa02) {
		this.aa02 = aa02;
	}
	public String getAb01() {
		return ab01;
	}
	public void setAb01(String ab01) {
		this.ab01 = ab01;
	}
	public String getAb11() {
		return ab11;
	}
	public void setAb11(String ab11) {
		this.ab11 = ab11;
	}
	public Long getAb02() {
		return ab02;
	}
	public void setAb02(Long ab02) {
		this.ab02 = ab02;
	}
	public String getDelays() {
		return delays;
	}
	public void setDelays(String delays) {
		this.delays = delays;
	}
}
