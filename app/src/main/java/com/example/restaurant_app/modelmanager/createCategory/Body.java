package com.example.restaurant_app.modelmanager.createCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body {

    @SerializedName("Categoryname")
    @Expose
    private String Categoryname;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    public String getCategoryname() {
        return Categoryname;
    }

    public void setCategoryname(String Categoryname) {
        Categoryname = Categoryname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
