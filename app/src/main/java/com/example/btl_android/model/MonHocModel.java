package com.example.btl_android.model;

public class MonHocModel {
    private int id;
    private String tenMonHoc;
    private int soTin;

    public MonHocModel(String tenMonHoc, int soTin) {
        this.tenMonHoc = tenMonHoc;
        this.soTin = soTin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getSoTin() {
        return soTin;
    }

    public void setSoTin(int soTin) {
        this.soTin = soTin;
    }
}
