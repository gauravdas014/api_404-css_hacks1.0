package com.example.nirog.data.model;

public class User_Details {

    private String _id;

    private String name;

    private String phone;

    private String email;

    private Integer __v;

    private String token;

    public User_Details(String _id, String name, String phone, String email, int __v, String token) {
        this._id = _id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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


    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
