package com.example.nirog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VaccineDetails {

    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    @SerializedName("dose")
    @Expose
    private String dose;

    @SerializedName("site")
    @Expose
    private String site;

    @SerializedName("route")
    @Expose
    private String route;

    @SerializedName("smallDescription")
    @Expose
    private String smallDescription;

    @SerializedName("__v")
    @Expose
    private Integer __v;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("_id")
    @Expose
    private String _id;

    @SerializedName("whenToGive")
    @Expose
    private String whenToGive;

    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public VaccineDetails(String createdAt, String dose, String site, String route, String smallDescription, Integer __v, String name, String description, String _id, String whenToGive, String updatedAt) {
        this.createdAt = createdAt;
        this.dose = dose;
        this.site = site;
        this.route = route;
        this.smallDescription = smallDescription;
        this.__v = __v;
        this.name = name;
        this.description = description;
        this._id = _id;
        this.whenToGive = whenToGive;
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWhenToGive() {
        return whenToGive;
    }

    public void setWhenToGive(String whenToGive) {
        this.whenToGive = whenToGive;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
