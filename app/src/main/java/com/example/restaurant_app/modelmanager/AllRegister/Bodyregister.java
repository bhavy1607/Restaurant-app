
package com.example.restaurant_app.modelmanager.AllRegister;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bodyregister {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private Integer phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("activerole")
    @Expose
    private String activerole;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActiverole() {
        return activerole;
    }

    public void setActiverole(String activerole) {
        this.activerole = activerole;
    }

}
