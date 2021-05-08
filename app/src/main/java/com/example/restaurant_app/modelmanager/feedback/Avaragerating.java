
package com.example.restaurant_app.modelmanager.feedback;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Avaragerating {

    @SerializedName("rating")
    @Expose
    private Integer rating;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
