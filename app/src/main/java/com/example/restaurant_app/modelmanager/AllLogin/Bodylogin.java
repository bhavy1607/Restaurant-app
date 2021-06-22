
package com.example.restaurant_app.modelmanager.AllLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bodylogin {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
