package com.example.btl_android.model;

public class DiemThanhPhan {
    private int ID_Diem;
    private int ID_MonHoc;
    private int ID_SinhVien;
    private double diemChuyenCan;
    private double diemBTL;
    private double diemThi;

    public DiemThanhPhan(int ID_MonHoc, int ID_SinhVien, double diemChuyenCan, double diemBTL, double diemThi) {
        this.ID_MonHoc = ID_MonHoc;
        this.ID_SinhVien = ID_SinhVien;
        this.diemChuyenCan = diemChuyenCan;
        this.diemBTL = diemBTL;
        this.diemThi = diemThi;
    }

    public int getID_Diem() {
        return ID_Diem;
    }

    public void setID_Diem(int ID_Diem) {
        this.ID_Diem = ID_Diem;
    }

    public int getID_MonHoc() {
        return ID_MonHoc;
    }

    public void setID_MonHoc(int ID_MonHoc) {
        this.ID_MonHoc = ID_MonHoc;
    }

    public int getID_SinhVien() {
        return ID_SinhVien;
    }

    public void setID_SinhVien(int ID_SinhVien) {
        this.ID_SinhVien = ID_SinhVien;
    }

    public double getDiemChuyenCan() {
        return diemChuyenCan;
    }

    public void setDiemChuyenCan(double diemChuyenCan) {
        this.diemChuyenCan = diemChuyenCan;
    }

    public double getDiemBTL() {
        return diemBTL;
    }

    public void setDiemBTL(double diemBTL) {
        this.diemBTL = diemBTL;
    }

    public double getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(double diemThi) {
        this.diemThi = diemThi;
    }
}
