package com.example.nirog.data.model;

public class ResponseHospital {

    private String status;
    private HospitalDetails hospitalDetails;

    public ResponseHospital(String status, HospitalDetails hospitalDetails) {
        this.status = status;
        this.hospitalDetails = hospitalDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HospitalDetails getHospitalDetails() {
        return hospitalDetails;
    }

    public void setHospitalDetails(HospitalDetails hospitalDetails) {
        this.hospitalDetails = hospitalDetails;
    }
}
