package com.example.btl_android;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.btl_android.model.DiemThanhPhan;
import com.example.btl_android.model.MonHocModel;
import com.example.btl_android.model.SinhVienModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database.db";
    public static final int version = 1;
    public static final String TABLE_SV = "QLSV";
    public static final String TABLE_MH = "QLMH";
    public static final String TABLE_Diem = "QLDiem";

    public static final String ID_SV = "idSV";

    public static final String hoTen = "HoTen";
    public static final String queQuan = "QueQuan";
    public static final String namSinh = "NamSinh";

    public static final String ID_Mon = "idMon";

    public static final String tenMon = "TenMon";
    public static final String soTin = "SoTin";


    private String ID_Diem = "ID_Diem";
    private String ID_MonHoc = "ID_MonHoc";
    private String ID_SinhVien = "ID_SinhVien";
    private String diemChuyenCan = "DiemChuyenCan";
    private String diemBTL = "DiemBTL";
    private String diemThi = "DiemThi";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlSV = "CREATE TABLE " + TABLE_SV + " ("
                + ID_SV + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + hoTen + " TEXT NOT NULL,"
                + queQuan + " TEXT NOT NULL,"
                + namSinh + " TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sqlSV);


        String sqlMH = "CREATE TABLE " + TABLE_MH + " ("
                + ID_Mon + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + tenMon + " TEXT NOT NULL,"
                + soTin + " INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(sqlMH);


        String sqlDiem = "CREATE TABLE " + TABLE_Diem + " ("
                + ID_Diem + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                + ID_MonHoc + " INTEGER NOT NULL,"
                + ID_SinhVien + " INTEGER NOT NULL,"
                + diemChuyenCan + " REAL NOT NULL,"
                + diemBTL + " REAL NOT NULL,"
                + diemThi + " REAL NOT NULL)";
        sqLiteDatabase.execSQL(sqlDiem);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addSinhVien(SinhVienModel sinhVienModel) {
        if (sinhVienModel != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(hoTen, sinhVienModel.getHoTen());
            contentValues.put(queQuan, sinhVienModel.getQueQuan());
            contentValues.put(namSinh, sinhVienModel.getNamSinh());
            long response = db.insert(TABLE_SV, null, contentValues);
            db.close();
            if (response > -1) return false;
            return true;
        }
        return false;
    }

    public boolean updateSinhVien(int id, SinhVienModel sinhVienModel) {
        if (id >= 0 && sinhVienModel != null) {
            SQLiteDatabase db = getReadableDatabase();
            String whereClause = ID_SV + " =?";
            String[] whereArs = {id + ""};
            ContentValues contentValues = new ContentValues();
            contentValues.put(hoTen, sinhVienModel.getHoTen());
            contentValues.put(queQuan, sinhVienModel.getQueQuan());
            contentValues.put(namSinh, sinhVienModel.getNamSinh());
            db.update(TABLE_SV, contentValues, whereClause, whereArs);
            return true;
        }
        return false;
    }

    public boolean deleteSinhVien(int id) {
        if (id >= 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            String whereClause = ID_SV + " =?";
            String[] whereArs = {id + ""};
            db.delete(TABLE_SV, whereClause, whereArs);
            db.close();
            return true;
        }
        return false;
    }


    @SuppressLint("Range")
    public ArrayList<SinhVienModel> getSV() {
        ArrayList<SinhVienModel> res = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_SV;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                SinhVienModel sinhVienModel = new SinhVienModel(cursor.getString(cursor.getColumnIndex(hoTen)), cursor.getString(cursor.getColumnIndex(queQuan)), cursor.getInt(cursor.getColumnIndex(namSinh)));
                sinhVienModel.setID_SV(cursor.getInt(cursor.getColumnIndex(ID_SV)));
                res.add(sinhVienModel);
            }
        }
        return res;
    }


    public boolean addMonHoc(MonHocModel model) {
        if (model != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(tenMon, model.getTenMonHoc());
            contentValues.put(soTin, model.getSoTin());
            long response = db.insert(TABLE_MH, null, contentValues);
            db.close();
            if (response > -1) return false;
            return true;
        }
        return false;
    }

    public boolean updateMonHoc(int id, MonHocModel monHocModel) {
        if (id >= 0 && monHocModel != null) {
            SQLiteDatabase db = getReadableDatabase();
            String whereClause = ID_Mon + " =?";
            String[] whereArs = {id + ""};
            ContentValues contentValues = new ContentValues();
            contentValues.put(tenMon, monHocModel.getTenMonHoc());
            contentValues.put(soTin, monHocModel.getSoTin());
            db.update(TABLE_MH, contentValues, whereClause, whereArs);
            return true;
        }
        return false;
    }

    public boolean deleteMonHoc(int id) {
        if (id >= 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            String whereClause = ID_Mon + " =?";
            String[] whereArs = {id + ""};
            db.delete(TABLE_MH, whereClause, whereArs);
            db.close();
            return true;
        }
        return false;
    }


    @SuppressLint("Range")
    public ArrayList<MonHocModel> getMonHoc() {
        ArrayList<MonHocModel> res = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_MH;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                MonHocModel monHocModel = new MonHocModel(cursor.getString(cursor.getColumnIndex(tenMon)), cursor.getInt(cursor.getColumnIndex(soTin)));
                monHocModel.setId(cursor.getInt(cursor.getColumnIndex(ID_Mon)));
                res.add(monHocModel);
            }
        }
        return res;
    }


    public boolean addDiem(DiemThanhPhan model) {
        if (model != null) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID_MonHoc, model.getID_MonHoc());
            contentValues.put(ID_SinhVien, model.getID_SinhVien());
            contentValues.put(diemChuyenCan, model.getDiemChuyenCan());
            contentValues.put(diemBTL, model.getDiemBTL());
            contentValues.put(diemThi, model.getDiemThi());
            long response = db.insert(TABLE_Diem, null, contentValues);
            db.close();
            if (response > -1) return false;
            return true;
        }
        return false;
    }

    public boolean updateDiem(int id, DiemThanhPhan model) {
        if (id >= 0 && model != null) {
            SQLiteDatabase db = getReadableDatabase();
            String whereClause = ID_Diem + " =?";
            String[] whereArs = {id + ""};
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID_MonHoc, model.getID_MonHoc());
            contentValues.put(ID_SinhVien, model.getID_SinhVien());
            contentValues.put(diemChuyenCan, model.getDiemChuyenCan());
            contentValues.put(diemBTL, model.getDiemBTL());
            contentValues.put(diemThi, model.getDiemThi());
            db.update(TABLE_Diem, contentValues, whereClause, whereArs);
            return true;
        }
        return false;
    }

    public boolean deleteDiem(int id) {
        if (id >= 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            String whereClause = ID_Diem + " =?";
            String[] whereArs = {id + ""};
            db.delete(TABLE_Diem, whereClause, whereArs);
            db.close();
            return true;
        }
        return false;
    }


    @SuppressLint("Range")
    public ArrayList<DiemThanhPhan> getDiemThanhPhan() {
        ArrayList<DiemThanhPhan> res = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_Diem;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                DiemThanhPhan diemThanhPhan = new DiemThanhPhan(cursor.getInt(cursor.getColumnIndex(ID_MonHoc))
                        , cursor.getInt(cursor.getColumnIndex(ID_SinhVien))
                        , cursor.getDouble(cursor.getColumnIndex(diemChuyenCan))
                        , cursor.getDouble(cursor.getColumnIndex(diemBTL))
                        , cursor.getDouble(cursor.getColumnIndex(diemThi))
                );
                diemThanhPhan.setID_Diem(cursor.getInt(cursor.getColumnIndex(ID_Diem)));
                res.add(diemThanhPhan);
            }
        }
        return res;
    }


    public SinhVienModel findSVById(int id) {
        SinhVienModel sinhVienModel = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_SV + " WHERE " + ID_SV + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                sinhVienModel = new SinhVienModel(cursor.getString(cursor.getColumnIndex(hoTen)), cursor.getString(cursor.getColumnIndex(queQuan)), cursor.getInt(cursor.getColumnIndex(namSinh)));
                sinhVienModel.setID_SV(cursor.getInt(cursor.getColumnIndex(ID_SV)));

            }
        }
        return sinhVienModel;
    }


    public MonHocModel findMHById(int id) {
        MonHocModel monHocModel = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_MH + " WHERE " + ID_Mon + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                monHocModel = new MonHocModel(cursor.getString(cursor.getColumnIndex(tenMon)), cursor.getInt(cursor.getColumnIndex(soTin)));
                monHocModel.setId(cursor.getInt(cursor.getColumnIndex(ID_Mon)));

            }
        }
        return monHocModel;
    }




}