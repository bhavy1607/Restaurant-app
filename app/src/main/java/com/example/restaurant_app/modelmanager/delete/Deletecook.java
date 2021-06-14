
package com.example.restaurant_app.modelmanager.delete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deletecook {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("RemovedPerson")
    @Expose
    private RemovedPerson removedPerson;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RemovedPerson getRemovedPerson() {
        return removedPerson;
    }

    public void setRemovedPerson(RemovedPerson removedPerson) {
        this.removedPerson = removedPerson;
    }

}
