package com.example.nirog.data.model;

public class Get_User {

    private String _id;

    private String name;

    private String phone;

    private String email;

    private Integer __v;

    private String password;

    private String token;

    public Get_User(String _id, String name, String phone, String email, Integer __v, String password, String token) {
        this._id = _id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.__v = __v;
        this.password = password;
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

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
