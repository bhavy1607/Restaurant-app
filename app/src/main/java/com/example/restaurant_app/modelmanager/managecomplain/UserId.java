
package com.example.restaurant_app.modelmanager.managecomplain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserId {

    @SerializedName("otps")
    @Expose
    private List<Object> otps = null;
    @SerializedName("posts")
    @Expose
    private List<Object> posts = null;
    @SerializedName("feedbacks")
    @Expose
    private List<Object> feedbacks = null;
    @SerializedName("orders")
    @Expose
    private List<String> orders = null;
    @SerializedName("activerole")
    @Expose
    private String activerole;
    @SerializedName("roles")
    @Expose
    private List<String> roles = null;
    @SerializedName("restaurantId")
    @Expose
    private List<Object> restaurantId = null;
    @SerializedName("complaints")
    @Expose
    private List<String> complaints = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("created_At")
    @Expose
    private String createdAt;
    @SerializedName("updated_At")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public List<Object> getOtps() {
        return otps;
    }

    public void setOtps(List<Object> otps) {
        this.otps = otps;
    }

    public List<Object> getPosts() {
        return posts;
    }

    public void setPosts(List<Object> posts) {
        this.posts = posts;
    }

    public List<Object> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Object> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    public String getActiverole() {
        return activerole;
    }

    public void setActiverole(String activerole) {
        this.activerole = activerole;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Object> getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(List<Object> restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<String> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<String> complaints) {
        this.complaints = complaints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
