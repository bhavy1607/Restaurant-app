
package com.example.restaurant_app.modelmanager.showrevenuemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sumrevenue {

    @SerializedName("grandtotal")
    @Expose
    private Double grandtotal;

    public Double getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(Double grandtotal) {
        this.grandtotal = grandtotal;
    }
}
