package org.jiangtao.utils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by MrJiang on 2016/3/17.
 * collections单例
 * 创建连接
 */
public class Collections {
    private static Collections mCollections;
    public static String databaseUrl = "jdbc:mysql://159.203.129.92:3306/monkey?user=mysql&password=123456&autoReconnect=true&failOverReadOnly=false";

    private Collections() {
    }

    public static Collections getInstance() {
        if (mCollections == null) {
            synchronized (Collections.class) {
                mCollections = new Collections();
            }
        }
        return mCollections;
    }

    /**
     * 打开数据库连接
     *
     * @return
     */
    public ConnectionSource openConnectionResource() {
        ConnectionSource connectionSource = null;
        try {
            connectionSource =
                    new JdbcConnectionSource(databaseUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connectionSource;
    }

    /**
     * 关闭数据库连接
     *
     * @param connectionSource
     */
    public void closeConnectionResource(ConnectionSource connectionSource) {
        try {
            connectionSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
