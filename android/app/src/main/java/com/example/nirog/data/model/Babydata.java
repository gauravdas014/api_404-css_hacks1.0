package com.example.nirog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Babydata {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;

    @SerializedName("monthOfBirth")
    @Expose
    private String monthOfBirth;

    @SerializedName("yearOfBirth")
    @Expose
    private String yearOfBirth;

    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("motherName")
    @Expose
    private String motherName;

    @SerializedName("fatherName")
    @Expose
    private String fatherName;




    public Babydata(String name, String dateOfBirth, String monthOfBirth, String yearOfBirth, String age, String motherName, String fatherName) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.age = age;
        this.motherName = motherName;
        this.fatherName = fatherName;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }




}
