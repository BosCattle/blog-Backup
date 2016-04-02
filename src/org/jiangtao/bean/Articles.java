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
  private int accountId;
  @DatabaseField(columnName = "article_address", canBeNull = false, unique = true)
  private String articleAddress;

  public Articles(int accountId, String articleAddress) {
    this.accountId = accountId;
    this.articleAddress = articleAddress;
  }

  public Articles(int id, int accountId, String articleAddress) {
    this.id = id;
    this.accountId = accountId;
    this.articleAddress = articleAddress;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  public String getArticleAddress() {
    return articleAddress;
  }

  public void setArticleAddress(String articleAddress) {
    this.articleAddress = articleAddress;
  }

  @Override public String toString() {
    return "Articles{" +
        "id=" + id +
        ", accountId=" + accountId +
        ", articleAddress='" + articleAddress + '\'' +
        '}';
  }
}
