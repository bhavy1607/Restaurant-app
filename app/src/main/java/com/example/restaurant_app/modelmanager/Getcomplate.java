package com.example.restaurant_app.modelmanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Getcomplate {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("complaints")
    @Expose
    private List<Complaint> complaints = null;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

}
