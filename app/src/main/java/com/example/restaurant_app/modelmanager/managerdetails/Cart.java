
package com.example.restaurant_app.modelmanager.managerdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart {

    @SerializedName("items")
    @Expose
    private List<Object> items = null;

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

}
