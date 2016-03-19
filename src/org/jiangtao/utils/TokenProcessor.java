package org.jiangtao.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MrJiang on 2016/3/19.
 * token 生成工具
 */
public class TokenProcessor {
    private static TokenProcessor instance;

    private long previous;

    protected TokenProcessor() {
    }

    public static TokenProcessor getInstance() {
        if (instance == null) {
            synchronized (TokenProcessor.class) {
                instance = new TokenProcessor();
            }
        }
        return instance;
    }

    /**
     * 生成token
     *
     * @param msg
     * @param timeChange
     * @return
     */
    public synchronized String generateToken(String msg, boolean timeChange) {
        try {

            long current = System.currentTimeMillis();
            if (current == previous) current++;
            previous = current;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            if (timeChange) {
                byte now[] = (new Long(current)).toString().getBytes();
                md.update(now);
            }
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }

        return sb.toString();
    }
}
