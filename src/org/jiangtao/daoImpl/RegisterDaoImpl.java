package org.jiangtao.daoImpl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.jiangtao.bean.Accounts;
import org.jiangtao.utils.Collections;
import org.jiangtao.utils.TokenProcessor;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by MrJiang on 2016/3/20.
 * 注册中使用的工具类
 */
public class RegisterDaoImpl {
    private static RegisterDaoImpl instance;
    private ConnectionSource collections;
    private Dao<Accounts, String> accountDao;

    private RegisterDaoImpl() {
    }

    public static RegisterDaoImpl getInstance() {
        if (instance == null) {
            synchronized (RegisterDaoImpl.class) {
                instance = new RegisterDaoImpl();
            }
        }
        return instance;
    }

    /**
     * 返回true
     * 可以添加
     * 返回false
     * 数据库中已经存在
     *
     * @param phone
     * @return
     */
    public boolean isExits(String phone) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            accountDao = DaoManager.createDao(collections, Accounts.class);
            ArrayList<Accounts> accountsArrayList = (ArrayList<Accounts>) accountDao.queryForAll();
            for (Accounts accounts : accountsArrayList) {
                if (phone.equals(accounts.getPhone())) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return true;
    }

    /**
     * 保存数据库
     * 返回account
     *
     * @param phone
     * @param password
     * @return
     */
    public Accounts getAccounts(String phone, String password) {
        collections = Collections.getInstance().openConnectionResource();
        String token = TokenProcessor.getInstance().generateToken(phone, false);
        Accounts accounts = new Accounts(token, phone, password);
        try {
            accountDao = DaoManager.createDao(collections, Accounts.class);
            assert accountDao != null;
            accountDao.createOrUpdate(accounts);
            ArrayList<Accounts> accountsArrayList = (ArrayList<Accounts>) accountDao.queryForAll();
            for (Accounts accounts2 : accountsArrayList) {
                if (accounts2.getToken().equals(token)) {
                    return accounts2;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }
}
