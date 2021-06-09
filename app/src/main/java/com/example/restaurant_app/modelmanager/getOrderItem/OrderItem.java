
package com.example.restaurant_app.modelmanager.getOrderItem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderItem {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("order")
    @Expose
    private List<Order> order = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

}
