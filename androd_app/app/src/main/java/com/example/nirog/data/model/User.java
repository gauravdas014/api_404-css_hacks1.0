package com.example.nirog.data.model;

public class User {
    String _id;
    String name;
    String phone;
    String email;
    String address;
    String __v;
    String token;

    public User(String _id, String name, String phone, String email, String address, String __v, String token) {
        this._id = _id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.__v = __v;
        this.token = token;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
