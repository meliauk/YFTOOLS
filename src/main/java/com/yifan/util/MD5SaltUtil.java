package com.yifan.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author YIFan
 * @Date 2020/6/20 11:33
 * @Version 1.0
 */
public class MD5SaltUtil {

    public static String MD5Salt(String salt,String obj){
        ByteSource bytes = ByteSource.Util.bytes(salt);
        SimpleHash simpleHash=new SimpleHash("MD5", obj, salt, 1024);
        return simpleHash.toString();

    }

}
