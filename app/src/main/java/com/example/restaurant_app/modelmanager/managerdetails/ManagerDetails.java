
package com.example.restaurant_app.modelmanager.managerdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManagerDetails {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list = null;
    @SerializedName("totalPersons")
    @Expose
    private Integer totalPersons;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public Integer getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(Integer totalPersons) {
        this.totalPersons = totalPersons;
    }

}
