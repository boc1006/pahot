package com.boc.common.core.test.mybatis;

import java.util.List;

/**
 * 存储Mybatis执行的SQL,并转换成LIST
 * <p>@Title: SqlThreadLocal.java 
 * <p>@Package com.boc.common.core.test.mybatis 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月19日 上午9:35:00 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class SqlThreadLocal {
    public static final ThreadLocal<List<String>> threadlocal = new ThreadLocal<List<String>>();

    public static void set(List<String> object) {
        threadlocal.set(object);
    }

    public static List<String> get() {
        return threadlocal.get();
    }

}
