package cn.pahot.xa.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boc.common.biz.IdWorkerUtil;
import com.boc.common.core.biz.AbstractPageBuilder;
import com.boc.common.core.biz.DefaultPageBuilder;
import com.boc.common.enums.PropertiesHelper;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.page.PageParams;
import com.boc.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import cn.pahot.xa.dao.CapXaMapper;
import cn.pahot.xa.entity.ExecutionLogger;
import cn.pahot.xa.entity.MessageEntity;
import cn.pahot.xa.entity.MessagePubEntity;
import cn.pahot.xa.entity.MessageSubEntity;
import cn.pahot.xa.enums.MessageLiveStateEnum;
import cn.pahot.xa.enums.MessageStateEnum;
import cn.pahot.xa.exception.CapXaBizException;

/**
 * 分布式事务业务处理
 * <p>@Title CapXaBiz</p>
 * <p>@Description com.dgg.message.xa.biz</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author Administrator</p>
 * <p>@date 2017年2月16日</p>
 * <p>huangjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Transactional
@Service("capXaBiz")
public class CapXaBiz {
    @Autowired
    private CapXaMapper<MessageEntity> capDao;
    @Autowired
    private CapXaMapper<DTO<String, String>> capDTODao;

    @Autowired
    private JmsTemplate notifyJmsTemplate;

    /**
     * 保存消息为待确认状态
     *
     * @param messageEntity
     * @return
     */
    public Long saveMessageWaitingConfirm(MessageEntity messageEntity) throws BizException {

        if (StringUtils.isEmpty(messageEntity)) {
            throw CapXaBizException.INSTANCE.newInstance("消息对象不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getContent())) {
            throw CapXaBizException.INSTANCE.newInstance("消息内容不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getType())) {
            throw CapXaBizException.INSTANCE.newInstance("消息数据类型不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getSource())) {
            throw CapXaBizException.INSTANCE.newInstance("消息数据来源编号不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getQueuename())) {
            throw CapXaBizException.INSTANCE.newInstance("消息队列名称不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getExt1())) {
            throw CapXaBizException.INSTANCE.newInstance("数据源标识ID不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getAa01())) {
            throw CapXaBizException.INSTANCE.newInstance("消息创建者名称不能为空!");
        }

        messageEntity.setState(MessageStateEnum.CONFIRMING.key);
        messageEntity.setIsdead(MessageLiveStateEnum.ALIVED.key);
        messageEntity.setAa02(Long.parseLong(DateUtils.getCurrDateTime()));
        messageEntity.setRepeatcount(0);
        messageEntity.setExt2("1");//点对点模式

        if (capDao.saveMessage(messageEntity) > 0) {
            return messageEntity.getId();
        } else {
            throw CapXaBizException.INSTANCE.newInstance(JSON.toJSONString(messageEntity));
        }
    }

    /**
     * 保存订阅消息为待确认状态
     *
     * @param subId   消息订阅编号
     * @param content 消息主体
     * @param remark  消息备注
     * @param aa01    消息创建人编号
     * @param aa11    消息创建人姓名
     * @param dbID    数据库ID
     * @return 消息发布编号
     * @throws BizException
     */
    public long saveSubMessageWaitingConfirm(int subId, String content, String remark, String aa01, String aa11) throws BizException {
        long pid = IdWorkerUtil.getId();
        if (subId == 0 || StringUtils.isEmpty(content)) {
            throw CapXaBizException.INSTANCE.newInstance("订阅消息ID或订阅消息内容不能为空:subid=" + subId + "\tcontent=" + content);
        }
        if (StringUtils.isEmpty(aa01) || StringUtils.isEmpty(aa11)) {
            throw CapXaBizException.INSTANCE.newInstance("操作人编号或操作人姓名不能为空:aa01=" + aa01 + "\taa11=" + aa11);
        }

        //先根据subId查询MessageSubEntity
        MessageSubEntity sub = capDao.getSubMessageForSubid(subId);
        if (StringUtils.isEmpty(sub)) {
            throw CapXaBizException.INSTANCE.newInstance("未找到subId为【" + subId + "】的订阅信息");
        }

        if (sub.getState().equals("2")) {//该订阅消息已停用
            return pid * -1l;
        }

        //构造List<MessageEntity>
        List<MessageEntity> lstMsg = new ArrayList<MessageEntity>();
        //构造List<MessagePubEntity>
        List<MessagePubEntity> lstPub = new ArrayList<MessagePubEntity>();
        String queue[] = sub.getQueuename().split(",");
        String delay[] = sub.getDelays().split(",");
        int idx = -1;
        for (String queueName : queue) {
            ++idx;
            if (StringUtils.isEmpty(queueName)) continue;
            MessageEntity me = new MessageEntity();
            me.setContent(content);
            me.setType(sub.getType());
            me.setSource(sub.getSource());
            me.setQueuename(queueName);
            me.setState(MessageStateEnum.CONFIRMING.key);
            me.setIsdead(MessageLiveStateEnum.ALIVED.key);
            me.setDelaytime(DateUtils.getCurrOrNextMinuteTime(Integer.valueOf(delay[idx])));
            me.setRemark(remark);
            me.setAa01(aa01);
            me.setAa11(aa11);
            me.setAa02(Long.parseLong(DateUtils.getCurrDateTime()));
            me.setExt1(sub.getDbid());
            me.setExt2("2");//发布订阅模式
            lstMsg.add(me);
        }
        capDao.insertMsgForBatch(lstMsg);

        for (MessageEntity me : lstMsg) {
            MessagePubEntity pub = new MessagePubEntity();
            pub.setMid(me.getId());
            pub.setSid(sub.getId());
            pub.setPid(pid);
            pub.setContents(content);
            pub.setState(MessageStateEnum.CONFIRMING.key);
            pub.setDbid(sub.getDbid());
            pub.setRemark(remark);
            pub.setCreatetime(Long.parseLong(DateUtils.getCurrDateTime()));
            lstPub.add(pub);
        }

        capDao.insertPubForBatch(lstPub);

        return pid;
    }

    /**
     * 确认并发送消息.
     *
     * @param messageId
     * @throws BizException
     */
    public void confirmAndSendMessage(String messageId) throws BizException {
        if (StringUtils.isEmpty(messageId)) {
            throw CapXaBizException.XA_DEFAULT_NULL;
        }

        DTO<String, String> param = new BaseDTO<String, String>();
        param.put("id", messageId);
        param.put("state", MessageStateEnum.CONFIRMING.key);

        final MessageEntity msgEntity = capDao.queryMessageByDTO(param);

        if (StringUtils.isEmpty(msgEntity)) {
            throw CapXaBizException.INSTANCE.newInstance("未找消息编号为【" + messageId + "】且未确认的消息！");
        }

        //判断当前时间是否到达消息队列延迟时间
        if (msgEntity.getDelaytime() > DateUtils.getCurrDateTimeToLong()) {
            msgEntity.setState(MessageStateEnum.CONFIRMED.key);
            msgEntity.setAb02(DateUtils.getCurrDateTimeToLong());
            capDao.modifyMessageByCondition(msgEntity);
            return;
        }

        msgEntity.setState(MessageStateEnum.SENDDING.key);
        msgEntity.setAb02(DateUtils.getCurrDateTimeToLong());

        capDao.modifyMessageByCondition(msgEntity);

        notifyJmsTemplate.setDefaultDestinationName(msgEntity.getQueuename());
        notifyJmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(msgEntity));
            }
        });
    }

    /**
     * 确认并发送订阅消息
     *
     * @param pubId 消息发布编号
     * @throws BizException
     */
    public void confirmAndSendSubMessage(long pubId) throws BizException {
        if (pubId == 0) {
            throw CapXaBizException.INSTANCE.newInstance("消息发布编号不能为0！");
        }

        List<MessagePubEntity> lstPub = capDao.queryPubMessageForPid(pubId);
        if (null != lstPub)
            for (MessagePubEntity pub : lstPub) {
                confirmAndSendMessage(String.valueOf(pub.getMid()));
            }
    }

    /**
     * 确认并发送消息（批量）.
     *
     * @param messageId
     * @throws BizException
     */
    public void confirmAndSendMessageForList(List<String> messageIds) throws BizException {
        if (messageIds == null || messageIds.size() == 0) {
            throw CapXaBizException.XA_DEFAULT_NULL;
        }
        List<String> nullMessageIds = new ArrayList<String>();
        for (String messageId : messageIds) {

            DTO<String, String> param = new BaseDTO<String, String>();
            param.put("id", messageId);
            param.put("state", MessageStateEnum.CONFIRMING.key);

            final MessageEntity msgEntity = capDao.queryMessageByDTO(param);

            if (StringUtils.isEmpty(msgEntity)) {
                nullMessageIds.add(messageId);
                continue;
            }

            //判断当前时间是否到达消息队列延迟时间
            if (msgEntity.getDelaytime() > DateUtils.getCurrDateTimeToLong()) {
                msgEntity.setState(MessageStateEnum.CONFIRMED.key);
                msgEntity.setAb02(DateUtils.getCurrDateTimeToLong());

                capDao.modifyMessageByCondition(msgEntity);
                continue;
            }

            msgEntity.setState(MessageStateEnum.SENDDING.key);
            msgEntity.setAb02(DateUtils.getCurrDateTimeToLong());

            capDao.modifyMessageByCondition(msgEntity);

            notifyJmsTemplate.setDefaultDestinationName(msgEntity.getQueuename());
            notifyJmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(JSONObject.toJSONString(msgEntity));
                }
            });
        }

        //注释下面,未找到的不用抛出异常
//    	if(nullMessageIds.size() > 0) {
//    		StringBuffer sb = new StringBuffer();
//    		for(String nullMessageId:nullMessageIds) {
//    			sb.append(nullMessageId).append(",");
//    		}
//    		throw CapXaBizException.INSTANCE.newInstance("未找消息编号为【" + sb.toString() + "】且未确认的消息！");
//    	}
    }

    /**
     * 保存并发送消息
     *
     * @param messageEntity
     * @return
     */
    public int saveAndSendMessage(final MessageEntity messageEntity) throws BizException {

        if (StringUtils.isEmpty(messageEntity)) {
            throw CapXaBizException.XA_DEFAULT_NULL;
        }

        if (StringUtils.isEmpty(messageEntity.getContent())) {
            throw CapXaBizException.INSTANCE.newInstance("消息内容不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getType())) {
            throw CapXaBizException.INSTANCE.newInstance("消息数据类型不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getSource())) {
            throw CapXaBizException.INSTANCE.newInstance("消息数据来源编号不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getQueuename())) {
            throw CapXaBizException.INSTANCE.newInstance("消息队列名称不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getExt1())) {
            throw CapXaBizException.INSTANCE.newInstance("数据源标识ID不能为空!");
        }

        if (StringUtils.isEmpty(messageEntity.getAa01())) {
            throw CapXaBizException.INSTANCE.newInstance("消息创建者名称不能为空!");
        }

        messageEntity.setState(MessageStateEnum.SENDDING.key);
        messageEntity.setAa02(DateUtils.getCurrDateTimeToLong());
        messageEntity.setIsdead(MessageLiveStateEnum.ALIVED.key);
        messageEntity.setRepeatcount(0);
        messageEntity.setExt2("1");//点对点模式

        //判断当前时间是否到达消息队列延迟时间
        if (messageEntity.getDelaytime() > DateUtils.getCurrDateTimeToLong()) {
            messageEntity.setState(MessageStateEnum.CONFIRMED.key);
            return capDao.saveMessage(messageEntity);
        }

        int result = capDao.saveMessage(messageEntity);

        notifyJmsTemplate.setDefaultDestinationName(messageEntity.getQueuename());
        notifyJmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(messageEntity));
            }
        });
        return result;
    }

    /**
     * 保存并发送订阅消息
     *
     * @param subId   消息订阅编号
     * @param content 消息主体
     * @param remark  消息备注
     * @param aa01    消息创建人编号
     * @param aa11    消息创建人姓名
     * @return 消息发布编号
     * @throws BizException
     */
    public long saveAndSendSubMessage(int subId, String content, String remark, String aa01, String aa11) throws BizException {
        long pid = IdWorkerUtil.getId();
        if (subId == 0 || StringUtils.isEmpty(content)) {
            throw CapXaBizException.INSTANCE.newInstance("订阅消息ID或订阅消息内容不能为空:subid=" + subId + "\tcontent=" + content);
        }
        if (StringUtils.isEmpty(aa01)) {
            throw CapXaBizException.INSTANCE.newInstance("操作人编号不能为空:aa01=" + aa01);
        }

        //先根据subId查询MessageSubEntity
        MessageSubEntity sub = capDao.getSubMessageForSubid(subId);
        if (StringUtils.isEmpty(sub)) {
            throw CapXaBizException.INSTANCE.newInstance("未找到subId为【" + subId + "】的订阅信息");
        }

        if (sub.getState().equals("2")) {//该订阅消息已停用
            return pid * -1l;
        }

        //构造List<MessageEntity>
        List<MessageEntity> lstMsg = new ArrayList<MessageEntity>();
        //构造List<MessagePubEntity>
        List<MessagePubEntity> lstPub = new ArrayList<MessagePubEntity>();
        String queue[] = sub.getQueuename().split(",");
        String delay[] = sub.getDelays().split(",");
        int idx = -1;
        for (String queueName : queue) {
            ++idx;
            if (StringUtils.isEmpty(queueName)) continue;
            MessageEntity me = new MessageEntity();
            me.setContent(content);
            me.setType(sub.getType());
            me.setSource(sub.getSource());
            me.setQueuename(queueName);
            me.setState(MessageStateEnum.SENDDING.key);
            me.setIsdead(MessageLiveStateEnum.ALIVED.key);
            me.setDelaytime(DateUtils.getCurrOrNextMinuteTime(Integer.valueOf(delay[idx])));
            me.setRemark(remark);
            me.setAa01(aa01);
            me.setAa11(aa11);
            me.setAa02(Long.parseLong(DateUtils.getCurrDateTime()));
            me.setExt1(sub.getDbid());
            me.setExt2("2");//发布订阅模式
            lstMsg.add(me);
        }
        capDao.insertMsgForBatch(lstMsg);

        for (final MessageEntity me : lstMsg) {
            MessagePubEntity pub = new MessagePubEntity();
            pub.setMid(me.getId());
            pub.setSid(sub.getId());
            pub.setPid(pid);
            pub.setContents(content);
            pub.setState(MessageStateEnum.SENDDING.key);
            pub.setDbid(sub.getDbid());
            pub.setRemark(remark);
            pub.setCreatetime(Long.parseLong(DateUtils.getCurrDateTime()));

            if (me.getDelaytime() > DateUtils.getCurrDateTimeToLong()) {
                pub.setState(MessageStateEnum.CONFIRMED.key);
                lstPub.add(pub);
                continue;
            }

            notifyJmsTemplate.setDefaultDestinationName(me.getQueuename());
            notifyJmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(JSONObject.toJSONString(me));
                }
            });

            lstPub.add(pub);
        }

        capDao.insertPubForBatch(lstPub);
        return pid;
    }

    /**
     * 直接发送消息
     *
     * @param messageEntity
     */
    public void directSendMessage(final MessageEntity messageEntity) throws BizException {
        if (StringUtils.isEmpty(messageEntity)) {
            throw CapXaBizException.XA_DEFAULT_NULL;
        }

        if (StringUtils.isEmpty(messageEntity.getQueuename())) {
            throw CapXaBizException.INSTANCE.newInstance("消息队列名称不能为空!");
        }

        notifyJmsTemplate.setDefaultDestinationName(messageEntity.getQueuename());
        notifyJmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(messageEntity));
            }
        });
    }

    /**
     * 批量发送已确认且已达到延迟发送时间的消息
     */
    public void sendConfirmedAndDelaydMessage() {
        DTO<String, String> param = new BaseDTO<String, String>();
        // 获取配置的开始处理的时间
        param.put("sortType", "ASC"); // 分页查询的排序方式，正向排序
        param.put("state", MessageStateEnum.CONFIRMED.key);// 取状态为已确认的消息
        param.put("isdead", MessageLiveStateEnum.ALIVED.key);// 取存活的发送中消息
        param.put("delaytime", DateUtils.getCurrDateTime());//达到发延迟发送时间

        List<MessageEntity> lstDTO = getMessageList(param);
        if (StringUtils.isEmpty(lstDTO) || lstDTO.size() == 0) {
            return;
        }

        for (MessageEntity me : lstDTO) {
            notifyJmsTemplate.setDefaultDestinationName(me.getQueuename());
            notifyJmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(JSONObject.toJSONString(me));
                }
            });

            me.setState(MessageStateEnum.SENDDING.key);
            me.setAb02(DateUtils.getCurrDateTimeToLong());
            capDao.modifyMessageByCondition(me);
        }
    }

    /**
     * 重试发送消息
     *
     * @param messageEntity
     */
    public void reSendMessage(final MessageEntity messageEntity) throws BizException {
        if (StringUtils.isEmpty(messageEntity)) {
            throw CapXaBizException.XA_DEFAULT_NULL;
        }

        if (StringUtils.isEmpty(messageEntity.getQueuename())) {
            throw CapXaBizException.INSTANCE.newInstance("消息队列名称不能为空!");
        }

        //判断当前时间是否到达消息队列延迟时间
        if (messageEntity.getDelaytime() > DateUtils.getCurrDateTimeToLong()) {
            return;
        }

        MessageEntity msgParam = new MessageEntity();
        msgParam.setId(messageEntity.getId());
        msgParam.setRepeatcount(messageEntity.getRepeatcount() + 1);
        msgParam.setAb02(DateUtils.getCurrDateTimeToLong());

        capDao.modifyMessageByCondition(msgParam);

        notifyJmsTemplate.setDefaultDestinationName(messageEntity.getQueuename());
        notifyJmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(messageEntity));
            }
        });
    }

    /**
     * 根据messageId重发某条消息.
     *
     * @param messageId
     */
    public void reSendMessageByMessageId(String messageId) throws BizException {
        if (StringUtils.isEmpty(messageId)) {
            throw CapXaBizException.XA_DEFAULT_NULL;
        }

        DTO<String, String> param = new BaseDTO<String, String>();
        param.put("id", messageId);
        param.put("state", MessageStateEnum.SENDDING.key);

        final MessageEntity msgEntity = capDao.queryMessageByDTO(param);

        if (StringUtils.isEmpty(msgEntity)) {
            throw CapXaBizException.INSTANCE.newInstance("未找消息编号为【" + messageId + "】且处于发送中的消息！");
        }

        //判断当前时间是否到达消息队列延迟时间
        if (msgEntity.getDelaytime() > DateUtils.getCurrDateTimeToLong()) {
            return;
        }

        int maxTimes = Integer.parseInt(PropertiesHelper.MESSAGE_MAX_SEND_TIMES);//该处通过配置进行优化

        if (msgEntity.getRepeatcount() >= maxTimes) {
            msgEntity.setIsdead(MessageLiveStateEnum.DEAD.key);
        }

        msgEntity.setAb02(Long.parseLong(DateUtils.getCurrDateTime()));
        msgEntity.setRepeatcount(msgEntity.getRepeatcount() + 1);

        capDao.modifyMessageByCondition(msgEntity);

        notifyJmsTemplate.setDefaultDestinationName(msgEntity.getQueuename());
        notifyJmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(msgEntity));
            }
        });
    }

    /**
     * 将消息标记为死亡消息.
     *
     * @param messageId
     */
    public void setMessageToAreadlyDead(String messageId) throws BizException {
        if (StringUtils.isEmpty(messageId)) {
            throw CapXaBizException.XA_DEFAULT_NULL;
        }
        DTO<String, String> param = new BaseDTO<String, String>();
        param.put("id", messageId);

        final MessageEntity msgEntity = capDao.queryMessageByDTO(param);
        if (StringUtils.isEmpty(msgEntity)) {
            throw CapXaBizException.INSTANCE.newInstance("未找消息编号为【" + messageId + "】的消息！");
        }

        MessageEntity msgParam = new MessageEntity();
        msgParam.setId(Long.valueOf(messageId));
        msgParam.setIsdead(MessageLiveStateEnum.DEAD.key);
        msgParam.setAb02(DateUtils.getCurrDateTimeToLong());

        capDao.modifyMessageByCondition(msgParam);
    }

    /**
     * 根据消息ID获取消息
     *
     * @param messageId
     * @return
     */
    public MessageEntity getMessageByMessageId(String messageId) throws BizException {
        if (StringUtils.isEmpty(messageId)) {
            throw CapXaBizException.XA_DEFAULT_NULL;
        }
        DTO<String, String> param = new BaseDTO<String, String>();
        param.put("id", messageId);

        return capDao.queryMessageByDTO(param);

    }

    /**
     * 根据消息ID删除消息
     *
     * @param messageId
     */
    @Transactional
    public void deleteMessageByMessageId(String messageId) throws BizException {
        if (StringUtils.isEmpty(messageId)) {
            throw CapXaBizException.XA_DEFAULT_NULL;
        }

        DTO<String, String> param = new BaseDTO<String, String>();
        param.put("id", messageId);

        capDTODao.saveMessageToHistory(param);
        capDTODao.deleteMessageById(param);
    }

    /**
     * 删除订阅消息
     *
     * @param pid
     * @throws BizException
     */
    public void deleteMessageByPubMessageId(long pid) throws BizException {
        capDao.deleteMessageByPubMessageId(pid);
    }

    public void reSendAllDeadMessageByQueueName(String queueName, int batchSize) throws BizException {

        int numPerPage = 1000;
        if (batchSize > 0 && batchSize < 100) {
            numPerPage = 100;
        } else if (batchSize > 100 && batchSize < 5000) {
            numPerPage = batchSize;
        } else if (batchSize > 5000) {
            numPerPage = 5000;
        } else {
            numPerPage = 1000;
        }

        int pageNum = 1;
        DTO<String, String> paramMap = new BaseDTO<String, String>();
        paramMap.put("queuename", queueName);
        paramMap.put("isdead", MessageLiveStateEnum.DEAD.key);
        paramMap.put("sortType", "ASC");
        paramMap.put("delaytime", DateUtils.getCurrDateTime());

        Map<String, MessageEntity> messageMap = new HashMap<String, MessageEntity>();
        List<MessageEntity> recordList = new ArrayList<MessageEntity>();
        int pageCount = 1;

        PageParams<DTO<String, String>> pageParams = new PageParams<DTO<String, String>>();
        pageParams.setParams(paramMap);
        pageParams.setPageNo(pageNum);
        pageParams.setPageSize(numPerPage);

        PageInfo<MessageEntity> pageInfo = listPage(pageParams);
        recordList = pageInfo.getList();

        if (recordList == null || recordList.isEmpty()) {
            return;
        }
        pageCount = pageInfo.getPages();
        for (final MessageEntity message : recordList) {
            messageMap.put(String.valueOf(message.getId()), message);
        }

        for (pageNum = 2; pageNum <= pageCount; pageNum++) {

            pageParams.setPageNo(pageNum);
            pageInfo = listPage(pageParams);
            recordList = pageInfo.getList();

            if (recordList == null || recordList.isEmpty()) {
                break;
            }

            for (final MessageEntity message : recordList) {
                messageMap.put(String.valueOf(message.getId()), message);
            }
        }

        recordList = null;
        pageInfo = null;

        for (Map.Entry<String, MessageEntity> entry : messageMap.entrySet()) {
            final MessageEntity message = entry.getValue();

            message.setAb02(DateUtils.getCurrDateTimeToLong());
            capDao.modifyMessageByCondition(message);

            notifyJmsTemplate.setDefaultDestinationName(message.getQueuename());
            notifyJmsTemplate.send(new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(JSONObject.toJSONString(message));
                }
            });
        }

    }

    /**
     * 根据条件查询分页数据
     *
     * @param pageParam
     * @return
     * @throws BizException
     */
    public PageInfo<MessageEntity> listPage(PageParams<DTO<String, String>> params) throws BizException {
        PageInfo<MessageEntity> pi = DefaultPageBuilder.build(params,
                new AbstractPageBuilder<MessageEntity, DTO<String, String>>() {
                    @Override
                    public Page<MessageEntity> build(DTO<String, String> pageParam) {
                        return capDao.queryMessagelistPage(pageParam);
                    }
                });
        return pi;
    }

    /**
     * 获取指定条件下的所有消息
     *
     * @param param
     * @return
     */
    public List<MessageEntity> getMessageList(DTO<String, String> param) throws BizException {
        if (StringUtils.isEmpty(param)) {
            throw CapXaBizException.XA_DEFAULT_NULL;
        }
        return capDao.queryMessageByList(param);
    }

    /**
     * 获取指定条件下的订阅消息
     *
     * @param aa02
     * @return
     */
    public List<MessagePubEntity> getPubMessageList(long aa02) throws BizException {
        return capDao.queryPubMessageForConfirming(aa02);
    }

    /**
     * 添加执行日志
     *
     * @param logger
     */
    public void recordExecutionLog(ExecutionLogger logger) throws BizException {
        capDao.recordExecutionLog(logger);
    }

    public void saveSubMessage(MessageSubEntity sub) throws BizException {
        if (StringUtils.isEmpty(sub)) {
            throw CapXaBizException.INSTANCE.newInstance("save->订阅消息对象不能为空！");
        }

        if (sub.getId() == 0
                || StringUtils.isEmpty(sub.getSubname())
                || StringUtils.isEmpty(sub.getQueuename())
                || StringUtils.isEmpty(sub.getType())
                || StringUtils.isEmpty(sub.getDbid())
                || StringUtils.isEmpty(sub.getSource())
                || StringUtils.isEmpty(sub.getAa01())
                || StringUtils.isEmpty(sub.getAa11())) {
            throw CapXaBizException.INSTANCE.newInstance("save->订阅消息内容不为空：" + JSONObject.toJSONString(sub));
        }
        MessageSubEntity qsub = new MessageSubEntity();
        qsub.setId(sub.getId());
        List<MessageSubEntity> msList = capDao.querySubMessage(qsub);
        if (msList.size() > 0)
            throw CapXaBizException.PARAM_VERIFY_FAIL.newInstance("save->订阅编号不能重复：" + JSONObject.toJSONString(sub));
        sub.setAa02(Long.parseLong(DateUtils.getCurrDateTime()));
        capDao.saveSubMessage(sub);
    }

    public void updateSubMessage(MessageSubEntity sub) throws BizException {
        if (StringUtils.isEmpty(sub)) {
            throw CapXaBizException.INSTANCE.newInstance("update->订阅消息对象不能为空！");
        }

        if (sub.getId() == 0
                || StringUtils.isEmpty(sub.getSubname())
                || StringUtils.isEmpty(sub.getQueuename())
                || StringUtils.isEmpty(sub.getType())
                || StringUtils.isEmpty(sub.getDbid())
                || StringUtils.isEmpty(sub.getSource())
                || StringUtils.isEmpty(sub.getAb01())
                || StringUtils.isEmpty(sub.getAb11())) {
            throw CapXaBizException.INSTANCE.newInstance("update->订阅消息内容不为空：" + JSONObject.toJSONString(sub));
        }
        sub.setAb02(Long.parseLong(DateUtils.getCurrDateTime()));
        capDao.updateSubMessage(sub);
    }

    public void updateSubMessageStateForId(MessageSubEntity sub) throws BizException {
        if (StringUtils.isEmpty(sub)) {
            throw CapXaBizException.INSTANCE.newInstance("update->订阅消息对象不能为空！");
        }

        if (sub.getId() == 0
                || StringUtils.isEmpty(sub.getState())
                || StringUtils.isEmpty(sub.getAb01())
                || StringUtils.isEmpty(sub.getAb11())) {
            throw CapXaBizException.INSTANCE.newInstance("update->订阅消息内容不为空：" + JSONObject.toJSONString(sub));
        }

        if (!sub.getState().equals("1") && !sub.getState().equals("2") && !sub.getState().equals("0")) {
            throw CapXaBizException.INSTANCE.newInstance("updateState->订阅消息内容不能为空！");
        }

        sub.setAb02(Long.parseLong(DateUtils.getCurrDateTime()));

        capDao.updateSubMessageStateForId(sub);
    }

    public List<MessageSubEntity> querySubMessage(MessageSubEntity sub) throws BizException {
        return capDao.querySubMessage(sub);
    }

    public DTO<String, String> findMessageSubById(Integer id) throws BizException {
        if (id == 0) {
            throw CapXaBizException.INSTANCE.newInstance("id cannot be null");
        }
        return capDao.selectOneMessageSub(id);
    }

}
