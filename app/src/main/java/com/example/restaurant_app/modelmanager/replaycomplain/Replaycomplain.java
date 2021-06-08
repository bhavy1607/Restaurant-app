
package com.example.restaurant_app.modelmanager.replaycomplain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Replaycomplain {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("reply")
    @Expose
    private Reply reply;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

}
