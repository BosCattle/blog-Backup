package org.jiangtao.daoImpl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.jiangtao.bean.Accounts;
import org.jiangtao.bean.Collection;
import org.jiangtao.utils.Collections;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by MrJiang on 5/6/2016.
 */
public class CollectionsDaoImpl {

    private static CollectionsDaoImpl instance;
    private ConnectionSource collections;
    private Dao<Collection, String> articleDao;

    private CollectionsDaoImpl() {

    }

    public static CollectionsDaoImpl getInstance() {
        if (instance == null) {
            synchronized (Collections.class) {
                instance = new CollectionsDaoImpl();
            }
        }
        return instance;
    }

    /**
     * 获取用户是否关注这篇文章
     *
     * @param articleId
     * @param accountId
     * @return
     */
    public Collection getCollections(String articleId, String accountId) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            articleDao = DaoManager.createDao(collections, Collection.class);
            QueryBuilder<Collection, String> builder = articleDao.queryBuilder();
            builder.where().eq("article_id", articleId);
            ArrayList<Collection> collectionsList = (ArrayList<Collection>) builder.query();
            if (collectionsList != null && collectionsList.size() != 0) {
                for (Collection c : collectionsList) {
                    if (c.getAccount().getId() == Integer.valueOf(accountId)) {
                        return c;
                    }
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }

    /**
     * 添加收藏
     *
     * @param articleId
     * @param accountId
     * @return
     */
    public Collection addCollections(String articleId, String accountId) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            articleDao = DaoManager.createDao(collections, Collection.class);
            Collection collection = new Collection();
            collection.setArticleId(Integer.valueOf(articleId));
            Accounts account = AccountsDaoImpl.getInstance().getAccount(accountId);
            collection.setAccount(account);
            articleDao.create(collection);
            return collection;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }

    /**
     * 取消收藏
     *
     * @param articleId
     * @param accountId
     * @return
     */
    public Collection cancelCollections(String collectionsId, String articleId, String accountId) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            articleDao = DaoManager.createDao(collections, Collection.class);
            DeleteBuilder<Collection, String> builder = articleDao.deleteBuilder();
            Collection collections = getCollections(articleId, accountId);
            builder.where().eq("id", collectionsId);
            return collections;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }

    /**
     * 获取用户所有的收藏
     *
     * @param accountId
     * @return
     */
    public ArrayList<Collection> getAllCollections(String accountId) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            articleDao = DaoManager.createDao(collections, Collection.class);
            QueryBuilder<Collection, String> builder = articleDao.queryBuilder();
            ArrayList<Collection> collections = (ArrayList<Collection>) builder.query();
            ArrayList<Collection> collectionsPersonal = new ArrayList<>();
            for (Collection c : collections) {
                if (c.getAccount().getId() == Integer.valueOf(accountId)) {
                    collectionsPersonal.add(c);
                }
            }
            if (collectionsPersonal.size() != 0) {
                return collectionsPersonal;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }

}
