
package com.example.restaurant_app.modelmanager.AllLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alllogin {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("your_accessToken")
    @Expose
    private String yourAccessToken;
    @SerializedName("your_refreshToken")
    @Expose
    private String yourRefreshToken;
    @SerializedName("Id")
    @Expose
    private String id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getYourAccessToken() {
        return yourAccessToken;
    }

    public void setYourAccessToken(String yourAccessToken) {
        this.yourAccessToken = yourAccessToken;
    }

    public String getYourRefreshToken() {
        return yourRefreshToken;
    }

    public void setYourRefreshToken(String yourRefreshToken) {
        this.yourRefreshToken = yourRefreshToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
