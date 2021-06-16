
package com.example.restaurant_app.modelmanager.managerdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bodymanager {

    @SerializedName("activerole")
    @Expose
//    private String activerole;
//
//    public String getActiverole() {
//        return activerole;
//    }
//
//    public void setActiverole(String activerole) {
//        this.activerole = activerole;
//    }

    private String activerole;

    public Bodymanager(String activerole) {
        this.activerole = activerole;
    }
}
