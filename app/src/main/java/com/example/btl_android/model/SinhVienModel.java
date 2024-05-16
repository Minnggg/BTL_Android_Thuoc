package com.example.btl_android.model;

public class SinhVienModel {
    private   int ID_SV ;
    private   String hoTen ;
    private   String queQuan ;

    private   int namSinh ;

    public SinhVienModel(String hoTen, String queQuan, int namSinh) {
        this.hoTen = hoTen;
        this.queQuan = queQuan;
        this.namSinh = namSinh;

    }
    public int getID_SV() {
        return ID_SV;
    }

    public void setID_SV(int ID_SV) {
        this.ID_SV = ID_SV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
}
