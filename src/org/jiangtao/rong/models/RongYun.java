package org.jiangtao.rong.models;

/**
 * Created by MrJiang on 4/24/2016.
 */
public class RongYun {
    public String userId;
    public String userName;
    public String portraitUri;
    public String token;


    public RongYun(String userId, String userName, String portraitUri, String token) {
        this.userId = userId;
        this.userName = userName;
        this.portraitUri = portraitUri;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return portraitUri;
    }

    public void setPassWord(String portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
