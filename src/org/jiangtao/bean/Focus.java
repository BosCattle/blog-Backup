package org.jiangtao.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by MrJiang on 5/2/2016.
 */
@DatabaseTable(tableName = "focus")
public class Focus {

    @DatabaseField(id = true, indexName = "id", columnName = "id")
    private int id;
    @DatabaseField(columnName = "account_id", canBeNull = false)
    private int account_id;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Accounts account_focus;

    public Focus() {
    }

    public Focus(int id, int account_id, Accounts account_focus) {
        this.id = id;
        this.account_id = account_id;
        this.account_focus = account_focus;
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

    public Accounts getAccount_focus() {
        return account_focus;
    }

    public void setAccount_focus(Accounts account_focus) {
        this.account_focus = account_focus;
    }
}
