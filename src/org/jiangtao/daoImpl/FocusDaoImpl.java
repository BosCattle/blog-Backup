package org.jiangtao.daoImpl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.jiangtao.bean.Accounts;
import org.jiangtao.bean.Focus;
import org.jiangtao.bean.IsFocus;
import org.jiangtao.utils.Collections;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by MrJiang on 5/2/2016.
 */
public class FocusDaoImpl {

    private static FocusDaoImpl instance;
    private ConnectionSource collections;
    private Dao<Focus, String> mFocusDao;

    private FocusDaoImpl() {
    }

    public static FocusDaoImpl getInstance() {
        if (instance == null) {
            synchronized (CommentDaoImpl.class) {
                instance = new FocusDaoImpl();
            }
        }
        return instance;
    }

    /**
     * 添加关注
     *
     * @param account_id
     * @param focus_id
     * @return
     */
    public ArrayList<Focus> insertFocus(String account_id, String focus_id) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            mFocusDao = DaoManager.createDao(collections, Focus.class);
            Focus focus = new Focus();
            focus.setAccount_id(Integer.parseInt(account_id));
            Accounts accounts = AccountsDaoImpl.getInstance().getAccount(focus_id);
            if (!isFocus(account_id,focus_id).getIs_focus()) {
                if (accounts != null) {
                    focus.setAccount_focus(accounts);
                    mFocusDao.create(focus);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }

    /**
     * 获取自己关注的人
     *
     * @param account_id
     * @return
     */
    public ArrayList<Focus> getAllFocus(String account_id) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            mFocusDao = DaoManager.createDao(collections, Focus.class);
            QueryBuilder<Focus, String> builder = mFocusDao.queryBuilder();
            builder.where().eq("account_id", account_id);
            return (ArrayList<Focus>) builder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }

    /**
     * 取消关注
     *
     * @param account_id
     * @param focus_id
     * @return
     */
    public ArrayList<Focus> cancelFocus(String account_id, String focus_id) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            mFocusDao = DaoManager.createDao(collections, Focus.class);
            DeleteBuilder<Focus, String> builder = mFocusDao.deleteBuilder();
            builder.where().eq("account_id", account_id).and().eq("foucus_id", focus_id);
            builder.delete();
            return getAllFocus(account_id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }

    /**
     * 判断两个人是否已经关注
     *
     * @param account_id
     * @param focus_id
     * @return
     */
    public IsFocus isFocus(String account_id, String focus_id) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            mFocusDao = DaoManager.createDao(collections, Focus.class);
            QueryBuilder<Focus, String> builder = mFocusDao.queryBuilder();
            builder.where().eq("account_id", account_id);
            ArrayList<Focus> focuses = (ArrayList<Focus>) builder.query();
            IsFocus isFocus = new IsFocus();
            for (Focus focus : focuses) {
                if (focus.getAccount_focus().getId() == Integer.valueOf(focus_id)) {
                    isFocus.setIs_focus(true);
                    return isFocus;
                }
            }
            isFocus.setIs_focus(false);
            return isFocus;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }

    /**
     * 获取关注我的人
     *
     * @param account_id
     * @return
     */
    public ArrayList<Focus> getPersonalAllFocus(String account_id) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            mFocusDao = DaoManager.createDao(collections, Focus.class);
            QueryBuilder<Focus, String> builder = mFocusDao.queryBuilder();
            ArrayList<Focus> personalFocus = new ArrayList<>();
            ArrayList<Focus> focus = (ArrayList<Focus>) builder.query();
            for (Focus focu : focus) {
                if (focu.getAccount_focus().getId() == Integer.valueOf(account_id)) {
                    personalFocus.add(focu);
                }
            }
            return personalFocus;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }
}
