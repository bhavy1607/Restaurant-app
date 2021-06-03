
package com.example.restaurant_app.modelmanager.parsal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Parsalorder {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;
    @SerializedName("totalOrders")
    @Expose
    private Integer totalOrders;

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

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

}
