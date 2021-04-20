
package com.example.restaurant_app.modeladmin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Staffdetails {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("persons")
    @Expose
    private List<Person> persons = null;
    @SerializedName("totalPersons")
    @Expose
    private Integer totalPersons;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Integer getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(Integer totalPersons) {
        this.totalPersons = totalPersons;
    }

}
