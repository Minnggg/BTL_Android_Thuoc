package com.example.btl_android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl_android.adapter.BaseAdapter1;
import com.example.btl_android.model.MonHocModel;
import com.example.btl_android.model.SinhVienModel;

public class QLActivity extends AppCompatActivity {
    BaseAdapter1 baseAdapter;
    DBHelper dbHelper;
    ListView rvListItems;
    Button btnThem;
    TextView tvHeader;
    String header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ql);
        dbHelper = new DBHelper(getApplicationContext());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        if (intent!=null){
            header = intent.getStringExtra("type");
        }
//        initdata();
        initView();
    }

    private void initdata() {
        dbHelper.addMonHoc(new MonHocModel("Mon hoc",3));
        dbHelper.addMonHoc(new MonHocModel("Mon hoc",3));
        dbHelper.addMonHoc(new MonHocModel("Mon hoc",3));
    }

    private void initView() {
        rvListItems = findViewById(R.id.rvListItems);
        tvHeader = findViewById(R.id.header);
        btnThem = findViewById(R.id.btnThem);
        if(header.equalsIgnoreCase("sinhvien")){
            tvHeader.setText("Quản lý Sinh Viên");
            baseAdapter = new BaseAdapter1(getApplicationContext(),dbHelper.getSV());
            rvListItems.setAdapter(baseAdapter);
            rvListItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    showDialogLuaChonSV(QLActivity.this,i);
                }
            });
        }
        if(header.equalsIgnoreCase("monhoc")){
            tvHeader.setText("Quản lý Môn Học");
            baseAdapter = new BaseAdapter1(getApplicationContext(),dbHelper.getMonHoc());
            rvListItems.setAdapter(baseAdapter);
            rvListItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    showDialogLuaChonMH(QLActivity.this,i);
                }
            });
        }
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(header.equalsIgnoreCase("sinhvien")){
                    showPopupThemSV(QLActivity.this);
                }
                if(header.equalsIgnoreCase("monhoc")){
                    showPopupThemMH(QLActivity.this);
                }
            }
        });
    }

    private void showDialogLuaChonSV(Context context, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bạn muốn");
        builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SinhVienModel sinhVienModel = (SinhVienModel) baseAdapter.getItem(i);
                showPopupsuaSV(context,sinhVienModel);
            }
        });

        builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SinhVienModel sinhVienModel = (SinhVienModel) baseAdapter.getItem(i);
                dbHelper.deleteSinhVien(sinhVienModel.getID_SV());
                baseAdapter.setData(dbHelper.getSV());
                Toast.makeText(context,"Xoa thanh cong",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        // Hiển thị Dialog
        builder.show();
    }


    private void showDialogLuaChonMH(Context context, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bạn muốn");
        builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MonHocModel monHocModel = (MonHocModel) baseAdapter.getItem(i);
                showPopupsuaMH(context,monHocModel);
            }
        });

        builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MonHocModel monHocModel = (MonHocModel) baseAdapter.getItem(i);
                dbHelper.deleteMonHoc(monHocModel.getId());
                baseAdapter.setData(dbHelper.getMonHoc());
                Toast.makeText(context,"Xoa thanh cong",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        // Hiển thị Dialog
        builder.show();
    }

    private void showPopupThemSV(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_edit);

        EditText ed1 = dialog.findViewById(R.id.ed1);
        EditText ed2 = dialog.findViewById(R.id.ed2);
        EditText ed3 = dialog.findViewById(R.id.ed3);
        EditText ed4 = dialog.findViewById(R.id.ed4);
        Button btn = dialog.findViewById(R.id.btnClick);

        ed1.setHint("Mã SV tự tăng không nhập");
        ed1.setFocusable(false);
        ed1.setFocusableInTouchMode(false);
        ed2.setHint("Nhập tên SV");
        ed3.setHint("Nhập quê quán SV");
        ed4.setHint("Nhập năm sinh SV");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed2.getText().toString().trim().length() !=0 &&ed3.getText().toString().trim().length() !=0 &&ed4.getText().toString().trim().length() !=0 ){
                    dbHelper.addSinhVien(new SinhVienModel(ed2.getText().toString().trim(),ed3.getText().toString().trim(),Integer.parseInt(ed4.getText().toString().trim())));
                    Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                    baseAdapter.setData(dbHelper.getSV());
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(context,"Kiểm tra lại thông tin sinh viên",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    private void showPopupThemMH(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_edit);

        EditText ed1 = dialog.findViewById(R.id.ed1);
        EditText ed2 = dialog.findViewById(R.id.ed2);
        EditText ed3 = dialog.findViewById(R.id.ed3);
        EditText ed4 = dialog.findViewById(R.id.ed4);
        ed4.setVisibility(View.GONE);
        Button btn = dialog.findViewById(R.id.btnClick);

        ed1.setHint("Mã MH tự tăng không nhập");
        ed1.setFocusable(false);
        ed1.setFocusableInTouchMode(false);
        ed2.setHint("Nhập tên MH");
        ed3.setInputType(InputType.TYPE_CLASS_NUMBER);
        ed3.setHint("Nhập số tín chỉ môn học");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed2.getText().toString().trim().length() !=0 &&ed3.getText().toString().trim().length() !=0){
                    dbHelper.addMonHoc(new MonHocModel(ed2.getText().toString().trim(),Integer.parseInt(ed3.getText().toString().trim())));
                    Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                    baseAdapter.setData(dbHelper.getMonHoc());
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(context,"Kiểm tra lại thông tin MH",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }


    private void showPopupsuaMH(Context context, MonHocModel monHocModel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_edit);

        EditText ed1 = dialog.findViewById(R.id.ed1);
        EditText ed2 = dialog.findViewById(R.id.ed2);
        EditText ed3 = dialog.findViewById(R.id.ed3);
        EditText ed4 = dialog.findViewById(R.id.ed4);
        ed4.setVisibility(View.GONE);
        ed3.setInputType(InputType.TYPE_CLASS_NUMBER);

        Button btn = dialog.findViewById(R.id.btnClick);
        btn.setText("Sửa");

        ed1.setText("MH"+monHocModel.getId());
        ed1.setFocusable(false);
        ed1.setFocusableInTouchMode(false);
        ed2.setText(monHocModel.getTenMonHoc());
        ed3.setText(monHocModel.getSoTin()+"");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed2.getText().toString().trim().length() !=0 &&ed3.getText().toString().trim().length() !=0){
                    dbHelper.updateMonHoc(monHocModel.getId(),new MonHocModel(ed2.getText().toString().trim(),Integer.parseInt(ed3.getText().toString().trim())));
                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                    baseAdapter.setData(dbHelper.getMonHoc());
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(context,"Kiểm tra lại thông tin MH",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    private void showPopupsuaSV(Context context, SinhVienModel sinhVienModel) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_edit);

        EditText ed1 = dialog.findViewById(R.id.ed1);
        EditText ed2 = dialog.findViewById(R.id.ed2);
        EditText ed3 = dialog.findViewById(R.id.ed3);
        EditText ed4 = dialog.findViewById(R.id.ed4);
        ed4.setInputType(InputType.TYPE_CLASS_NUMBER);

        Button btn = dialog.findViewById(R.id.btnClick);
        btn.setText("Sửa");

        ed1.setText("SV"+sinhVienModel.getID_SV());
        ed1.setFocusable(false);
        ed1.setFocusableInTouchMode(false);
        ed2.setText(sinhVienModel.getHoTen());
        ed3.setText(sinhVienModel.getQueQuan());
        ed4.setText(sinhVienModel.getNamSinh()+"");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed2.getText().toString().trim().length() !=0 &&ed3.getText().toString().trim().length() !=0 &&ed4.getText().toString().trim().length() !=0 ){
                    dbHelper.updateSinhVien(sinhVienModel.getID_SV(),new SinhVienModel(ed2.getText().toString().trim(),ed3.getText().toString().trim(),Integer.parseInt(ed4.getText().toString().trim())));
                    Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                    baseAdapter.setData(dbHelper.getSV());
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(context,"Kiểm tra lại thông tin sinh viên",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
}