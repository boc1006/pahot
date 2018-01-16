package com.boc.common.utils;

import com.boc.common.exception.BizException;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * creat by @author:chen in 2017\12\18 0018
 *
 * @description:字符串转成拼音
 **/
public class Hanyu4PinyinHelper {

    /* 将文字转为汉语拼音,待符号和空格*/
    public static String toHanyuPinyin(String chineselanguage) throws BizException {
        char[] cl_chars = chineselanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// 如果字符不是中文,则不转换
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new BizException(123, "当前内容包含不能转换成拼音的字符串");
        }
        return hanyupinyin;
    }

    /*将文字转为汉语拼音,去除符号和空格*/
    public static String getPinyinString(String ChineseLanguage) throws BizException {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                String str = String.valueOf(cl_chars[i]);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(
                            cl_chars[i], defaultFormat)[0];
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    hanyupinyin += cl_chars[i];
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    hanyupinyin += cl_chars[i];
                } else {// 什么都不做,不转换
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new BizException(123, "当前内容包含不能转换成拼音的字符串");
        }
        return hanyupinyin;
    }


    /* 取所有中第一个汉字的大写写首字母*/
    public static String getFirstLettersUp(String ChineseLanguage) throws BizException {
        return getFirstLetters(ChineseLanguage, HanyuPinyinCaseType.UPPERCASE);
    }

    /* 取所有中第一个汉字的小写首字母*/
    public static String getFirstLettersLo(String ChineseLanguage) throws BizException {
        return getFirstLetters(ChineseLanguage, HanyuPinyinCaseType.LOWERCASE);
    }

    /* 取所有中第一个汉字的首字母*/
    private static String getFirstLetters(String ChineseLanguage, HanyuPinyinCaseType caseType) throws BizException {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                String str = String.valueOf(cl_chars[i]);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0].substring(0, 1);
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    hanyupinyin += cl_chars[i];
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    hanyupinyin += cl_chars[i];
                } else {// 否则不转换
                    hanyupinyin += cl_chars[i];//如果是标点符号的话，带着
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new BizException(123, "当前内容包含不能转换成拼音的字符串");
        }
        return hanyupinyin;
    }


    /* 取第一个汉字的首字母*/
    public static String getFirstLetter(String ChineseLanguage) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            String str = String.valueOf(cl_chars[0]);
            if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(
                        cl_chars[0], defaultFormat)[0].substring(0, 1);
                ;
            } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                hanyupinyin += cl_chars[0];
            } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

                hanyupinyin += cl_chars[0];
            } else {// 否则不转换

            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }

    public static void main(String[] args) {
        System.out.println(Hanyu4PinyinHelper.toHanyuPinyin("多发的发独守空   ===aaaa====房阿道夫打发第三方"));


        System.out.println(Hanyu4PinyinHelper.getPinyinString("多发的发独守空   ===aaaa====房阿道夫打发第三方"));

        System.out.println(Hanyu4PinyinHelper.getFirstLetter("多发的发独守空   ===aaaa====房阿道夫打发第三方"));

        System.out.println(Hanyu4PinyinHelper.getFirstLettersLo("多发的发独守空   ===aaaa====房阿道夫打发第三方"));

        System.out.println(Hanyu4PinyinHelper.getFirstLettersUp("多发的发独守空   ===aaaa====房阿道夫打发第三方"));


    }
}