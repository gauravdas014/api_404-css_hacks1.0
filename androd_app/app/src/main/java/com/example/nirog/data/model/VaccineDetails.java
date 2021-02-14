package com.example.nirog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VaccineDetails {

    private String _id;

    private String name;

    private String whenToGive;

    private String dose;

    private String route;

    private String site;

    private String description;

    private String smallDescription;

    private String createdAt;

    private String updatedAt;

    private int __v;

    public VaccineDetails(String _id, String name, String whenToGive, String dose, String route, String site, String description, String smallDescription, String createdAt, String updatedAt, int __v) {
        this._id = _id;
        this.name = name;
        this.whenToGive = whenToGive;
        this.dose = dose;
        this.route = route;
        this.site = site;
        this.description = description;
        this.smallDescription = smallDescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
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

    public String getWhenToGive() {
        return whenToGive;
    }

    public void setWhenToGive(String whenToGive) {
        this.whenToGive = whenToGive;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
