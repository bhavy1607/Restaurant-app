
package com.example.restaurant_app.modelmanager.gettingorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Orderdetails {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

}
