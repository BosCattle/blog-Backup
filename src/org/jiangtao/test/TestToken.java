package org.jiangtao.test;

import org.jiangtao.utils.TokenProcessor;

/**
 * Created by MrJiang on 2016/3/19.
 * 测试token生成是否正确
 */
public class TestToken {
    public static void main(String[] args) {
        TokenProcessor processor = TokenProcessor.getInstance();
        String token = processor.generateToken("姜陶", true);
        System.out.println(token);
    }
}
