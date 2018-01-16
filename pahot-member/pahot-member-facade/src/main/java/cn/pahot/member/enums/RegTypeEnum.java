package cn.pahot.member.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册方式
 * <p>@Title: RegTypeEnum.java 
 * <p>@Package cn.pahot.member.enums 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月21日 上午11:41:20 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public enum RegTypeEnum {
	REG_TYPE_01("01", "会员注册"),
	REG_TYPE_02("02", "运营后台添加"),
	REG_TYPE_03("03", "业务自动注册"),
	REG_TYPE_00("00", "其它方式");

    // 成员变量
    public String key;
    public String value;

    // 构造方法
    private RegTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, RegTypeEnum> stringToEnum = new HashMap<String, RegTypeEnum>();

    static {
        for (RegTypeEnum blah : values()) {
            stringToEnum.put(blah.key, blah);
        }
    }

    public static RegTypeEnum toEnum(String symbol) {
        return stringToEnum.get(symbol);
    }
}
