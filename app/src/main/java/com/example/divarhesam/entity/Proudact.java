package com.example.divarhesam.entity;

public class Proudact {

    private String name;
    private String value;
    private String time;
    private int imgId;
    private String details;
    private String numberPhone;


    public Proudact(String name, String value, String time, int imgId, String details, String numberPhone) {
        this.name = name;
        this.value = value;
        this.time = time;
        this.imgId = imgId;
        this.details = details;
        this.numberPhone = numberPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}

