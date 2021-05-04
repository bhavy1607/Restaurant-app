
package com.example.restaurant_app.modelmanager.booktable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookTable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("tables")
    @Expose
    private List<Table> tables = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

}
