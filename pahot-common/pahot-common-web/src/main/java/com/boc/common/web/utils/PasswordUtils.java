package com.boc.common.web.utils;

import com.boc.common.utils.MD5Helper;
import com.dgg.common.security.DESPlus;

/**
 * 密码工具类
 * <p>@Title: PasswordUtils.java 
 * <p>@Package com.boc.common.web.utils 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月8日 下午1:29:33 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class PasswordUtils {
    /**
     * 加密密码信息
     *
     * @param pwd
     * @return
     */
    public static String encrypt(String pwd) {
        return MD5Helper.encrypt(pwd).toLowerCase();
    }

    /**
     * 加密密码信息
     *
     * @param pwd
     * @return
     */
    public static String encrypts(String pwd) {
        return new DESPlus("DGGERP962540ADMIN").encrypt(pwd);
    }

    /**
     * 解密密码信息
     *
     * @param pwd
     * @return
     */
    public static String decrypt(String pwd) {
        return new DESPlus("DGGERP962540ADMIN").decrypt(pwd);
    }

    /*public static void main(String[] args) {
        System.err.println(PasswordUtils.encrypts("15313545"));
        System.err.println(PasswordUtils.decrypt(PasswordUtils.encrypts("15313545")));
        System.err.println(PasswordUtils.decrypt("5ED763F210A9ECCBC54A950467338ED6A2C9A468800516CB409EB1D7ED50ED4D11C88FC9EDADF035BAC88B93C7630170"));
    }*/

    public static void main(String[] args) {
        String pwd = encrypt("000000");
        System.out.println(pwd);
    }
}
