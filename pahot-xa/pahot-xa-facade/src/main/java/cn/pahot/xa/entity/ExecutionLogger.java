package cn.pahot.xa.entity;

import java.io.Serializable;

/**
 * 消息
 * <p>@Title: ExecutionLogger.java 
 * <p>@Package cn.pahot.xa.entity 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月12日 下午2:10:12 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class ExecutionLogger implements Serializable {
	private static final long serialVersionUID = 3778414529411041522L;

    /**
     * 日志标识
     */
    private long id;
    /**
     * 消息编号
     */
    private long tid;
    /**
     * 处理业务系统ID
     */
    private String sid;
    /**
     * 处理描述
     */
    private String message;
    /**
     * 执行时消息状态
     */
    private String state;
    /**
     * 创建时间
     */
    private long createTime;

    public ExecutionLogger() {
    }

    public ExecutionLogger(long tid, String sid, String message, String state) {
        this.tid = tid;
        this.sid = sid;
        this.message = message;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    
}
