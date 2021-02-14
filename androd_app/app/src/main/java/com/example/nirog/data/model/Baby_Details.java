package com.example.nirog.data.model;

public class Baby_Details {
    String _id;
    String name;
    String age;
    String parent;
    String motherName;
    String fatherName;

    public Baby_Details(String _id, String name, String age, String parent, String motherName, String fatherName) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.parent = parent;
        this.motherName = motherName;
        this.fatherName = fatherName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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
