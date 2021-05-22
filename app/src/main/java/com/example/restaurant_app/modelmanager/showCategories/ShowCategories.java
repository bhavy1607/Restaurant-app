
package com.example.restaurant_app.modelmanager.showCategories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShowCategories {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("categoryposts")
    @Expose
    private List<Categorypost> categoryposts = null;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Categorypost> getCategoryposts() {
        return categoryposts;
    }

    public void setCategoryposts(List<Categorypost> categoryposts) {
        this.categoryposts = categoryposts;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

}
