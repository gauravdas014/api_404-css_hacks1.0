package com.example.nirog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGet_user {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("user")
    @Expose
    private Get_User get_user;

    public ResponseGet_user(String status, Get_User get_user) {
        this.status = status;
        this.get_user = get_user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Get_User getGet_user() {
        return get_user;
    }

    public void setGet_user(Get_User get_user) {
        this.get_user = get_user;
    }
}
