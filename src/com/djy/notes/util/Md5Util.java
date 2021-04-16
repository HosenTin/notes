package com.djy.notes.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 实现MD5加密的工具类
 */
public class Md5Util{
    public static String getMD5(String str) {
        /**
         * 加密后的字符串，被赋值后返回
         */
        String md5String = null;

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            md5String = new BigInteger(1,md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MD5加密失败");
        }
        return md5String;
    }
}