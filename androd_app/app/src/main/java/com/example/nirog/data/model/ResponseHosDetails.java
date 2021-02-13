package com.example.nirog.data.model;

import java.util.List;

public class ResponseHosDetails {

    private String status;
    private List<HospitalDetails> hospitalDetailsList;

    public ResponseHosDetails(String status, List<HospitalDetails> hospitalDetailsList) {
        this.status = status;
        this.hospitalDetailsList = hospitalDetailsList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<HospitalDetails> getHospitalDetailsList() {
        return hospitalDetailsList;
    }

    public void setHospitalDetailsList(List<HospitalDetails> hospitalDetailsList) {
        this.hospitalDetailsList = hospitalDetailsList;
    }
}
