package com.example.restaurant_app.modelmanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feedbackdetails {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("feedbacks")
    @Expose
    private List<Feedback> feedbacks = null;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

}
