package com.ajie.utils;

import java.security.MessageDigest;

/**
 * MD5加密
 * @Author 阿杰
 * @create 2022
 */
public class MD5Util {

    /**
     * 盐，用于混交md5
     */
    private static final String slat = "&%5123***&&%%$$#@";

    /**
     * 加密 无法解密
     * @param dataStr
     * @return
     */
    public static String md5(String dataStr) {
        try {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte[] s = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) {
        System.out.println(md5("123456"));
    }
}
