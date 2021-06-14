
package com.example.restaurant_app.modelmanager.waiterdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("otps")
    @Expose
    private java.util.List<Object> otps = null;
    @SerializedName("feedbacks")
    @Expose
    private java.util.List<Object> feedbacks = null;
    @SerializedName("orders")
    @Expose
    private java.util.List<Object> orders = null;
    @SerializedName("activerole")
    @Expose
    private String activerole;
    @SerializedName("roles")
    @Expose
    private java.util.List<String> roles = null;
    @SerializedName("categoryId")
    @Expose
    private Object categoryId;
    @SerializedName("complaints")
    @Expose
    private java.util.List<Object> complaints = null;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public java.util.List<Object> getOtps() {
        return otps;
    }

    public void setOtps(java.util.List<Object> otps) {
        this.otps = otps;
    }

    public java.util.List<Object> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(java.util.List<Object> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public java.util.List<Object> getOrders() {
        return orders;
    }

    public void setOrders(java.util.List<Object> orders) {
        this.orders = orders;
    }

    public String getActiverole() {
        return activerole;
    }

    public void setActiverole(String activerole) {
        this.activerole = activerole;
    }

    public java.util.List<String> getRoles() {
        return roles;
    }

    public void setRoles(java.util.List<String> roles) {
        this.roles = roles;
    }

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public java.util.List<Object> getComplaints() {
        return complaints;
    }

    public void setComplaints(java.util.List<Object> complaints) {
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
