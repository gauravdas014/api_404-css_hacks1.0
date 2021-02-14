package com.example.nirog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespomseBabyData {
    String status;

    @SerializedName("baby")
    @Expose
    private Baby_Details baby_details;
}
