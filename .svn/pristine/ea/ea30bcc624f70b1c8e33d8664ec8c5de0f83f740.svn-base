package cn.pahot.business.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 申请状态
 */
public enum BusinessApplayStateEnum {
    //01审核通过,02审核未通过,03提交审核'
    BUSINESS_APPLAY_STATE_PASS("01", "审核通过"),
    BUSINESS_APPLAY_STATE_REFUSE("02", "审核未通过"),
    BUSINESS_APPLAY_STATE_AUDIT("03", "提交审核");

    // 成员变量
    public String key;
    public String value;

    // 构造方法
    private BusinessApplayStateEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, BusinessApplayStateEnum> stringToEnum = new HashMap<String, BusinessApplayStateEnum>();

    static {
        for (BusinessApplayStateEnum en : values()) {
            stringToEnum.put(en.key, en);
        }
    }

    public static BusinessApplayStateEnum toEnum(String symbol) {
        return stringToEnum.get(symbol);
    }

}
