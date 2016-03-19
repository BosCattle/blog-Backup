package org.jiangtao.test;

import org.jiangtao.utils.Base64;


/**
 * Created by MrJiang on 2016/3/19.
 * 测试base64加密解密
 */
public class TestBase64 {
    public static void main(String[] args) throws Exception {
        Base64 base64 = Base64.getInstance();
        String str11 = "j3281033013991j";
        String buffer = base64.encodeBufferBase64(str11.getBytes());
        System.out.println(buffer);
        String userName = new String(base64.decodeBufferBase64(buffer));
        System.out.println(userName);
    }
}
