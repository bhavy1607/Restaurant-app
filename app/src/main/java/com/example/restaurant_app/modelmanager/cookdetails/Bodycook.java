
package com.example.restaurant_app.modelmanager.cookdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bodycook {

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

    public Bodycook(String activerole) {
        this.activerole = activerole;
    }
}
