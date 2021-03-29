package com.example.nirog.data.model;

import java.util.List;

public class HospitalDetailedModel {

    private String HospitalName;
    private String phone;
    private String email;
    private String info;
    private String image;
    private List<VaccineDetails> vaccineDetails;
    private List<DoctorDetails> doctorDetails;


    public HospitalDetailedModel(String hospitalName, String phone, String email, String info, String image, List<VaccineDetails> vaccineDetails, List<DoctorDetails> doctorDetails) {
        HospitalName = hospitalName;
        this.phone = phone;
        this.email = email;
        this.info = info;
        this.image = image;
        this.vaccineDetails = vaccineDetails;
        this.doctorDetails = doctorDetails;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<VaccineDetails> getVaccineDetails() {
        return vaccineDetails;
    }

    public void setVaccineDetails(List<VaccineDetails> vaccineDetails) {
        this.vaccineDetails = vaccineDetails;
    }

    public List<DoctorDetails> getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(List<DoctorDetails> doctorDetails) {
        this.doctorDetails = doctorDetails;
    }
}
