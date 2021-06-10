
package com.example.restaurant_app.modelmanager.TableWiseOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tablewiseorder {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("list")
    @Expose
    private List list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

}
