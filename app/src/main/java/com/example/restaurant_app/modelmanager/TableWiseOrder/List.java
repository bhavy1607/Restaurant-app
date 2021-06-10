
package com.example.restaurant_app.modelmanager.TableWiseOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("waiting")
    @Expose
    private Integer waiting;
    @SerializedName("orders")
    @Expose
    private java.util.List<Order> orders = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("table")
    @Expose
    private Integer table;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("availableTime")
    @Expose
    private Object availableTime;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("QRCode")
    @Expose
    private String qRCode;
    @SerializedName("currentUser")
    @Expose
    private CurrentUser currentUser;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @SerializedName("userName")
    @Expose
    private String userName;

    public Integer getWaiting() {
        return waiting;
    }

    public void setWaiting(Integer waiting) {
        this.waiting = waiting;
    }

    public java.util.List<Order> getOrders() {
        return orders;
    }

    public void setOrders(java.util.List<Order> orders) {
        this.orders = orders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(Object availableTime) {
        this.availableTime = availableTime;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getQRCode() {
        return qRCode;
    }

    public void setQRCode(String qRCode) {
        this.qRCode = qRCode;
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
