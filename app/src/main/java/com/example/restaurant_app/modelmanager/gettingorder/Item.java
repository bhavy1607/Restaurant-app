
package com.example.restaurant_app.modelmanager.gettingorder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("progress")
    @Expose
    private String progress;
    @SerializedName("ToKitchen")
    @Expose
    private Boolean toKitchen;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("notes")
    @Expose
    private Object notes;
    @SerializedName("product_id")
    @Expose
    private ProductId productId;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("productPrice")
    @Expose
    private Double productPrice;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("itemAcceptedAt")
    @Expose
    private String itemAcceptedAt;
    @SerializedName("itemDoneAt")
    @Expose
    private String itemDoneAt;
    @SerializedName("ingredientId")
    @Expose
    private String ingredientId;
    @SerializedName("ingredientPrice")
    @Expose
    private Double ingredientPrice;

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Boolean getToKitchen() {
        return toKitchen;
    }

    public void setToKitchen(Boolean toKitchen) {
        this.toKitchen = toKitchen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }



    public String getItemAcceptedAt() {
        return itemAcceptedAt;
    }

    public void setItemAcceptedAt(String itemAcceptedAt) {
        this.itemAcceptedAt = itemAcceptedAt;
    }

    public String getItemDoneAt() {
        return itemDoneAt;
    }

    public void setItemDoneAt(String itemDoneAt) {
        this.itemDoneAt = itemDoneAt;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getIngredientPrice() {
        return ingredientPrice;
    }

    public void setIngredientPrice(Double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
