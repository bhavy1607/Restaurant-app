package com.example.restaurant_app.modelmanager.createIngrediants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Body {

    @SerializedName("IngredientName")
    @Expose
    private String IngredientName;

    public String getIngredientName() {
        return IngredientName;
    }

    public void setIngredientName(String IngredientName) {
        this.IngredientName = IngredientName;
    }

    @SerializedName("price")
    @Expose
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
