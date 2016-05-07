package org.jiangtao.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by MrJiang on 5/6/2016.
 * 收藏model
 */
@DatabaseTable(tableName = "collection")
public class Collection {
    @DatabaseField(id = true, indexName = "id", columnName = "id")
    private int id;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Accounts account;
    @DatabaseField(columnName = "article_id", canBeNull = false)
    private int articleId;

    public Collection() {
    }

    public Collection(Accounts account, int articleId) {
        this.account = account;
        this.articleId = articleId;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", account=" + account +
                ", articleId=" + articleId +
                '}';
    }
}
