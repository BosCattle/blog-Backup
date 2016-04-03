package org.jiangtao.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by MrJiang on 4/2/2016. article model
 */
@DatabaseTable(tableName = "articles")
public class Articles {
  @DatabaseField(id = true, indexName = "id", columnName = "id")
  private int id;
  @DatabaseField(columnName = "account_id", canBeNull = false, unique = true)
  private int account_id;
  @DatabaseField(columnName = "article_address", canBeNull = false, unique = true)
  private String article_address;

  public Articles() {
  }

  public Articles(int accountId, String articleAddress) {
    this.account_id = accountId;
    this.article_address = articleAddress;
  }

  public Articles(int id, int accountId, String articleAddress) {
    this.id = id;
    this.account_id = accountId;
    this.article_address = articleAddress;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAccountId() {
    return account_id;
  }

  public void setAccountId(int accountId) {
    this.account_id = accountId;
  }

  public String getArticleAddress() {
    return article_address;
  }

  public void setArticleAddress(String articleAddress) {
    this.article_address = articleAddress;
  }

  @Override public String toString() {
    return "Articles{" +
        "id=" + id +
        ", accountId=" + account_id +
        ", articleAddress='" + article_address + '\'' +
        '}';
  }
}
