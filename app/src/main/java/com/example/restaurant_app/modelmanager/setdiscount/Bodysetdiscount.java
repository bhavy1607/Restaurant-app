package com.example.restaurant_app.modelmanager.setdiscount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bodysetdiscount {

    @SerializedName("discount")
    @Expose
    private String discount;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

}
