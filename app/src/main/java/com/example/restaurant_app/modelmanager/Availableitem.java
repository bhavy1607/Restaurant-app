
package com.example.restaurant_app.modelmanager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Availableitem {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("menu")
    @Expose
    private List<Menu> menu = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

}
