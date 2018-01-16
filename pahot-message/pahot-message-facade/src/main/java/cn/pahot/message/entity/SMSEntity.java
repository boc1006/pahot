package cn.pahot.message.entity;

import java.io.Serializable;

/**
 * 短信发送任务实体
 * <p>@Title: SMSEntity.java 
 * <p>@Package cn.pahot.message.entity 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月8日 下午6:30:40 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class SMSEntity implements Serializable{
	private static final long serialVersionUID = 4196983637878241464L;
	
	private Long id;
	private String channel;
	private String tid;
	private String sid;
	private String phones;
	private String contents;
	private String otherparams;
	private String busdesc;
	private String state;
	private Integer maxretry;
	private Integer retry;
	private Long bizinvoketime;
	private Long tasktime;
	private Long sendtime;
	private Long returetime;
	private String returnstate;
	private String returnmsg;
	private String returncontent;
	private Long modifytime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPhones() {
		return phones;
	}
	public void setPhones(String phones) {
		this.phones = phones;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getOtherparams() {
		return otherparams;
	}
	public void setOtherparams(String otherparams) {
		this.otherparams = otherparams;
	}
	public String getBusdesc() {
		return busdesc;
	}
	public void setBusdesc(String busdesc) {
		this.busdesc = busdesc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getMaxretry() {
		return maxretry;
	}
	public void setMaxretry(Integer maxretry) {
		this.maxretry = maxretry;
	}
	public Integer getRetry() {
		return retry;
	}
	public void setRetry(Integer retry) {
		this.retry = retry;
	}
	public Long getBizinvoketime() {
		return bizinvoketime;
	}
	public void setBizinvoketime(Long bizinvoketime) {
		this.bizinvoketime = bizinvoketime;
	}
	public Long getTasktime() {
		return tasktime;
	}
	public void setTasktime(Long tasktime) {
		this.tasktime = tasktime;
	}
	public Long getSendtime() {
		return sendtime;
	}
	public void setSendtime(Long sendtime) {
		this.sendtime = sendtime;
	}
	public Long getReturetime() {
		return returetime;
	}
	public void setReturetime(Long returetime) {
		this.returetime = returetime;
	}
	public String getReturnstate() {
		return returnstate;
	}
	public void setReturnstate(String returnstate) {
		this.returnstate = returnstate;
	}
	public String getReturnmsg() {
		return returnmsg;
	}
	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}
	public String getReturncontent() {
		return returncontent;
	}
	public void setReturncontent(String returncontent) {
		this.returncontent = returncontent;
	}
	public Long getModifytime() {
		return modifytime;
	}
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	
}
