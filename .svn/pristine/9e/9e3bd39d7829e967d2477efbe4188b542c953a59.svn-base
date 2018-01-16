package com.boc.common.core.test.mybatis;

import java.util.List;
import java.util.Map;

/**
 * 存储Mybatis执行的SQL
 * <p>@Title: SqlMapThreadLocal.java 
 * <p>@Package com.boc.common.core.test.mybatis 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月19日 上午9:32:56 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class SqlMapThreadLocal {
    public static final ThreadLocal<Map<String, List<String>>> threadlocal = new ThreadLocal<Map<String, List<String>>>();

    public static void set(Map<String, List<String>> object) {
        threadlocal.set(object);
    }

    public static Map<String, List<String>> get() {
        return  threadlocal.get();
    }

}
