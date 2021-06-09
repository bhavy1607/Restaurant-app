
package com.example.restaurant_app.modelmanager.showrevenuemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bodyshowrevenue {

    @SerializedName("startdate")
    @Expose
    private String startdate;
    @SerializedName("enddate")
    @Expose
    private String enddate;

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

}
