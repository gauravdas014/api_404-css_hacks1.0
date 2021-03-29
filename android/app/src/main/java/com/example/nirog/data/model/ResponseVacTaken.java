package com.example.nirog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseVacTaken {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("vaccines")
    @Expose
    private List<Vac_Details> vac_detailsList;

    public ResponseVacTaken(String status, List<Vac_Details> vac_detailsList) {
        this.status = status;
        this.vac_detailsList = vac_detailsList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Vac_Details> getVac_detailsList() {
        return vac_detailsList;
    }

    public void setVac_detailsList(List<Vac_Details> vac_detailsList) {
        this.vac_detailsList = vac_detailsList;
    }
}
