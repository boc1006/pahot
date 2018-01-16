package cn.pahot.xa.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息来源
 * 
 * @author hj87080234@gmail.com
 * @date 2016年9月13日
 */
public enum MessageSourceEnum {
	MSG_SOURCE_0001("0001", "CRM线下交易库存变更"),
	MSG_SOURCE_0002("0002","CRM线上交易库存变更"),
	MSG_SOURCE_0003("0003","CRM翼支付异步回调修改订单状态"),
	MSG_SOURCE_0004("0004","商家中心-站点商品数据处理"),
	MSG_SOURCE_0005("0005","小程序-顾问状态"),
	MSG_SOURCE_0007("0007","CRM-电话咨询"),
	MSG_SOURCE_0008("0008","400电话咨询");
	// 成员变量
	public String key;
	public String value;

	// 构造方法
	private MessageSourceEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private static final Map<String, MessageSourceEnum> stringToEnum = new HashMap<String, MessageSourceEnum>();
	static {
		for (MessageSourceEnum blah : values()) {
			stringToEnum.put(blah.key, blah);
		}
	}

	public static MessageSourceEnum toEnum(String symbol) {
		return stringToEnum.get(symbol);
	}
}
