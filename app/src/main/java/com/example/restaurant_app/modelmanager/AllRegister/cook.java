
package com.example.restaurant_app.modelmanager.AllRegister;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class cook {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Id")
    @Expose
    private String id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
