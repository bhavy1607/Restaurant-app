
package com.example.restaurant_app.modelmanager.updatewaiter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Waiterupdate {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("person")
    @Expose
    private Person person;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
