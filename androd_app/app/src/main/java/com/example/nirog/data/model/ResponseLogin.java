package com.example.nirog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("User")
    @Expose
    private User_Details user_details;

    public ResponseLogin(String status, User_Details user_details) {
        this.status = status;
        this.user_details = user_details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User_Details getUser_details() {
        return user_details;
    }

    public void setUser_details(User_Details user_details) {
        this.user_details = user_details;
    }
}
