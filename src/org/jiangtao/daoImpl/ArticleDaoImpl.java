package org.jiangtao.daoImpl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.jiangtao.bean.Articles;
import org.jiangtao.utils.Base64;
import org.jiangtao.utils.Collections;

/**
 * Created by MrJiang on 4/2/2016. article Dao
 */
public class ArticleDaoImpl {

  private static ArticleDaoImpl instance;
  private ConnectionSource collections;
  private Dao<Articles, String> articleDao;

  private ArticleDaoImpl() {
  }

  public static ArticleDaoImpl getInstance() {
    if (instance == null) {
      synchronized (ArticleDaoImpl.class) {
        instance = new ArticleDaoImpl();
      }
    }
    return instance;
  }

  /**
   * 插入文章
   */
  public Articles insertArticle(Articles articles) {
    collections = Collections.getInstance().openConnectionResource();
    try {
      articleDao = DaoManager.createDao(collections, Articles.class);
      articleDao.createOrUpdate(articles);
      List<Articles> articlesList = articleDao.queryForAll();
      for (Articles article : articlesList) {
        if (article.getAccountId() == articles.getId()) {
          return article;
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
   * 将文章写入本地，保存为html格式
   */
  public Articles writeArticle(String content, String accountId, String address)
      throws IOException {
    System.out.println(content);
    System.out.println(accountId);
    System.out.println("当前地址:" + address);
    File file = new File(address
        + "/web/"
        + Arrays.toString(Base64.getInstance().decodeBufferBase64(accountId + ""))
        + System.currentTimeMillis() + ".html");
    if (!file.exists()) {
      file.createNewFile();
      Articles article = new Articles(Integer.parseInt(accountId), file.getAbsolutePath());
      return article;
    }
    return null;
  }
}
