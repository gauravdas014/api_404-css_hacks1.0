package com.example.nirog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespomseBabyData {
    String status;

    @SerializedName("baby")
    @Expose
    private Baby_Details baby_details;

    public RespomseBabyData(String status, Baby_Details baby_details) {
        this.status = status;
        this.baby_details = baby_details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Baby_Details getBaby_details() {
        return baby_details;
    }

    public void setBaby_details(Baby_Details baby_details) {
        this.baby_details = baby_details;
    }
}
