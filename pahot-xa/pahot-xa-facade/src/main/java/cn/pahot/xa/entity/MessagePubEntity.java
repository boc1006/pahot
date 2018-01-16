package cn.pahot.xa.entity;

import java.io.Serializable;

/**
 * 分布式消息发布实体
 * <p>@Title CapXaBiz</p>
 * <p>@Description com.dgg.message.xa.biz</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年2月16日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class MessagePubEntity implements Serializable{
	private static final long serialVersionUID = -8642529594115213016L;
	private long mid;
	private int sid;
	private long pid;
	private String contents;
	private String state;
	private String dbid;
	private String remark;
	private Long createtime;
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
}
