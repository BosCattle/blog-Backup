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
  @DatabaseField(columnName = "content", canBeNull = false, unique = true)
  private String content;
  @DatabaseField(columnName = "title", canBeNull = false, unique = true)
  private String title;
  @DatabaseField(columnName = "image_url", canBeNull = true, unique = true)
  private String image_url;

  public Articles() {
  }

  public Articles(int accountId, String content) {
    this.account_id = accountId;
    this.content = content;
  }

  public Articles(int account_id, String content, String title, String image_url) {
    this.account_id = account_id;
    this.content = content;
    this.title = title;
    this.image_url = image_url;
  }

  public Articles(int id, int accountId, String content) {
    this.id = id;
    this.account_id = accountId;
    this.content = content;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAccount_id() {
    return account_id;
  }

  public void setAccount_id(int account_id) {
    this.account_id = account_id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  @Override public String toString() {
    return "Articles{" +
        "id=" + id +
        ", account_id=" + account_id +
        ", content='" + content + '\'' +
        ", title='" + title + '\'' +
        ", image_url='" + image_url + '\'' +
        '}';
  }
}
