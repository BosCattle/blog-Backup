package org.jiangtao.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by MrJiang on 2016/3/17.
 * des:用户账号页面
 */

@DatabaseTable(tableName = "accounts")
public class Accounts {
    @DatabaseField(id = true,indexName = "id",columnName = "id")
    private int id;
    @DatabaseField(columnName = "phone", canBeNull = false, unique = true)
    private String phone;
    @DatabaseField(columnName = "username", canBeNull = true)
    private String username;
    @DatabaseField(canBeNull = false, columnName = "password")
    private String password;
    @DatabaseField(canBeNull = true, columnName = "token")
    private String token;
    @DatabaseField(canBeNull = true, columnName = "image_url")
    private String image_url;
    @DatabaseField(canBeNull = true, columnName = "age")
    private String age;
    @DatabaseField(canBeNull = true, columnName = "sex")
    private String sex;
    @DatabaseField(canBeNull = true, columnName = "start_time")
    private String start_time;

    public Accounts() {
    }

    public Accounts(String token, String phone, String password) {
        this.token = token;
        this.phone = phone;
        this.password = password;
    }

    public Accounts(int id, String phone, String username, String password, String token) {
        this.id = id;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public Accounts(int id, String phone, String username, String password, String token, String image_url, String age,
                    String sex, String start_time) {
        this.id = id;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.token = token;
        this.image_url = image_url;
        this.age = age;
        this.sex = sex;
        this.start_time = start_time;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getSex() {
        return sex;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", image_url='" + image_url + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", start_time='" + start_time + '\'' +
                '}';
    }
}
