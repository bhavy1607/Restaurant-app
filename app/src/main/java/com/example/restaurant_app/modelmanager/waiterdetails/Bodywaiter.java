
package com.example.restaurant_app.modelmanager.waiterdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bodywaiter {

    @SerializedName("activerole")
    @Expose
    private String activerole;

    public String getActiverole() {
        return activerole;
    }

    public void setActiverole(String activerole) {
        this.activerole = activerole;
    }

}
