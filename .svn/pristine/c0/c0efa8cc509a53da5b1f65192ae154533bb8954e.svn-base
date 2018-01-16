package cn.pahot.member.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 终端类型
 * <p>@Title: TerminalEnum.java 
 * <p>@Package cn.pahot.member.enums 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月21日 上午11:41:10 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public enum TerminalEnum {
	TERMINAL_PC("01", "PC电脑"),
	TERMINAL_WX("02", "微信公众号"),
	TERMINAL_WAP("03", "手机WAP"),
	TERMINAL_APP("04", "APP"),
	TERMINAL_XCX("05", "小程序"),
	TERMINAL_OTHER("00", "其它");

    // 成员变量
    public String key;
    public String value;

    // 构造方法
    private TerminalEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private static final Map<String, TerminalEnum> stringToEnum = new HashMap<String, TerminalEnum>();

    static {
        for (TerminalEnum blah : values()) {
            stringToEnum.put(blah.key, blah);
        }
    }

    public static TerminalEnum toEnum(String symbol) {
        return stringToEnum.get(symbol);
    }
}
