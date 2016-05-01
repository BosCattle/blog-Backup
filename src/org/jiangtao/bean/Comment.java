package org.jiangtao.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by MrJiang on 5/1/2016.
 * 评论
 */
@DatabaseTable(tableName = "comment")
public class Comment {

    @DatabaseField(id = true, indexName = "id", columnName = "id")
    private int id;
    @DatabaseField(columnName = "parent_id",canBeNull = true)
    private int parent_id;
    @DatabaseField(columnName = "content",canBeNull = false)
    private String content;
    @DatabaseField(columnName = "create_at",canBeNull = false)
    private long create_at;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Accounts account;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Accounts parent_account;
    @DatabaseField(columnName = "article_id",canBeNull = false)
    private int article_id;

    public Comment() {
    }

    public Comment(int id, int parent_id, String content, long create_at, Accounts account, Accounts parent_account, int article_id) {
        this.id = id;
        this.parent_id = parent_id;
        this.content = content;
        this.create_at = create_at;
        this.account = account;
        this.parent_account = parent_account;
        this.article_id = article_id;
    }

    public Comment(int id, int parent_id, String content, long create_at, Accounts account, Accounts parent_account) {
        this.id = id;
        this.parent_id = parent_id;
        this.content = content;
        this.create_at = create_at;
        this.account = account;
        this.parent_account = parent_account;
    }

    public Comment(int parent_id, String content, long create_at, Accounts account, Accounts parent_account, int article_id) {
        this.parent_id = parent_id;
        this.content = content;
        this.create_at = create_at;
        this.account = account;
        this.parent_account = parent_account;
        this.article_id = article_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreate_at() {
        return create_at;
    }

    public void setCreate_at(long create_at) {
        this.create_at = create_at;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public Accounts getParent_account() {
        return parent_account;
    }

    public void setParent_account(Accounts parent_account) {
        this.parent_account = parent_account;
    }
}
