package cn.pahot.goods.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户状态
 * <p>@Title: UserStateEnum.java
 * <p>@Package cn.pahot.upms.auth.enums
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com
 * <p>@date 2017年11月21日 上午11:35:03
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public enum GoodsTypeStateEnum {
    GOODS_TYPE_STATE_NORMAL("01", "正常"),
    GOODS_TYPE_STATE_FORBIDDE("02", "禁用"),
    GOODS_TYPE_STATE_DELETE("00", "删除");

    // 成员变量
    public String key;
    public String value;

    // 构造方法
    private GoodsTypeStateEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, GoodsTypeStateEnum> stringToEnum = new HashMap<String, GoodsTypeStateEnum>();

    static {
        for (GoodsTypeStateEnum blah : values()) {
            stringToEnum.put(blah.key, blah);
        }
    }

    public static GoodsTypeStateEnum toEnum(String symbol) {
        return stringToEnum.get(symbol);
    }

}
