package org.jiangtao.daoImpl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jiangtao.bean.Articles;
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
        if (article.getAccount_id() == articles.getAccount_id()) {
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
  public Articles writeArticle(String content, String accountId, String address, String title,
      String imageUrl)
      throws Exception {
    System.out.println(content);
    System.out.println(accountId);
    System.out.println("当前地址:" + address);
    File file = new File(address
        + System.currentTimeMillis() + ".html");
    if (!file.exists()) {
      System.out.println("当前地址:" + file.getName());
      boolean isTrue = file.createNewFile();
      if (isTrue) {
        writeText(file.getPath(), content);
        return new Articles(Integer.parseInt(accountId), file.getName(), title, imageUrl);
      } else {
        throw new IOException("不能产生相应的文件");
      }
    }
    return null;
  }

  /**
   * 向文件中写入内容
   *
   * @throws Exception
   */
  public static void writeText(String path, String text) throws Exception {
    FileOutputStream o = null;
    try {
      o = new FileOutputStream(path);
      o.write(text.getBytes("UTF-8"));
    } catch (Exception e) {
      throw new Exception(e.toString());
    } finally {
      assert o != null;
      o.close();
    }
  }

  public ArrayList<Articles> getAllArticles(String articleId) {
    ArrayList<Articles> articles = new ArrayList<>();
    collections = Collections.getInstance().openConnectionResource();
    try {
      articleDao = DaoManager.createDao(collections, Articles.class);
      QueryBuilder<Articles, String> builder = articleDao.queryBuilder();
      builder.where()
          .between("id", Integer.parseInt(articleId),
              Integer.parseInt(articleId) + 20);
      articles = (ArrayList<Articles>) builder.query();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Collections.getInstance().closeConnectionResource(collections);
    }
    return articles;
  }
}
