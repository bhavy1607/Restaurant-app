
package com.example.restaurant_app.modelmanager.createCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Createcategories {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("category")
    @Expose
    private Category category;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
