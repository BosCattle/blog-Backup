package org.jiangtao.daoImpl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import org.jiangtao.bean.Accounts;
import org.jiangtao.bean.Comment;
import org.jiangtao.utils.Collections;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by MrJiang on 5/1/2016.
 * 包含获取文章所有评论，添加评论等内容
 */
public class CommentDaoImpl {

    private static CommentDaoImpl instance;
    private ConnectionSource collections;
    private Dao<Comment, String> mCommentDao;

    private CommentDaoImpl() {
    }

    public static CommentDaoImpl getInstance() {
        if (instance == null) {
            synchronized (CommentDaoImpl.class) {
                instance = new CommentDaoImpl();
            }
        }
        return instance;
    }

    /**
     * 获取文章的所有评论
     *
     * @param articleId
     * @return
     */
    public ArrayList<Comment> getAllComment(String articleId) {
        collections = Collections.getInstance().openConnectionResource();
        try {
            mCommentDao = DaoManager.createDao(collections, Comment.class);
            QueryBuilder<Comment, String> builder = mCommentDao.queryBuilder();
            builder.where().eq("article_id", String.valueOf(articleId));
            ArrayList<Comment> comments = (ArrayList<Comment>) builder.query();
            if (comments != null && comments.size() != 0) {
                return comments;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }

    /**
     * parent_id为0，表示只是评论,parent_id不为0，表示回复
     *
     * @param articleId
     * @param content
     * @param isParent
     * @param accountId
     * @param parentID
     * @return
     */
    public synchronized ArrayList<Comment> insertComment(int articleId, String content, int isParent, int accountId, int parentID) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setArticle_id(articleId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comment.setCreate_at(timestamp.getTime());
        comment.setParent_id(isParent);
        Accounts accounts = AccountsDaoImpl.getInstance().getAccount(String.valueOf(accountId));
        comment.setAccount(accounts);
        Accounts parentAccounts = AccountsDaoImpl.getInstance().getAccount(String.valueOf(parentID));
        comment.setParent_account(parentAccounts);
        collections = Collections.getInstance().openConnectionResource();
        try {
            mCommentDao = DaoManager.createDao(collections, Comment.class);
            mCommentDao.create(comment);
            return getAllComment(String.valueOf(articleId));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Collections.getInstance().closeConnectionResource(collections);
        }
        return null;
    }
}
