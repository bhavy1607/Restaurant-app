
package com.example.restaurant_app.modelmanager.TableWiseOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BodyTableWiseOrder {

    @SerializedName("table")
    @Expose
    private String table;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

}
