package com.example.divarhesam;

public class Rahnmagozaresh {
    private String mantn;
    private int imgId1;
    private int imgId2;

    public Rahnmagozaresh(String mantn, int imgId1, int imgId2) {
        this.mantn = mantn;
        this.imgId1 = imgId1;
        this.imgId2 = imgId2;
    }

    public String getMantn() {
        return mantn;
    }

    public void setMantn(String mantn) {
        this.mantn = mantn;
    }

    public int getImgId1() {
        return imgId1;
    }

    public void setImgId1(int imgId1) {
        this.imgId1 = imgId1;
    }

    public int getImgId2() {
        return imgId2;
    }

    public void setImgId2(int imgId2) {
        this.imgId2 = imgId2;
    }
}
