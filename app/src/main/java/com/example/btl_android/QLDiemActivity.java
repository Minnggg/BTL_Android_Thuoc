package com.example.btl_android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl_android.adapter.AdapterDiem;
import com.example.btl_android.adapter.AdapterSpiner;
import com.example.btl_android.model.DiemThanhPhan;
import com.example.btl_android.model.MonHocModel;
import com.example.btl_android.model.SinhVienModel;

import java.util.ArrayList;

public class QLDiemActivity extends AppCompatActivity {
    AdapterDiem adapterDiem;
    DBHelper dbHelper;

    ListView lvDiem;
    Button btnThem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_qldiem);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DBHelper(QLDiemActivity.this);
//        initData();
        initView(QLDiemActivity.this);

    }

    private void initData() {
        dbHelper.addDiem(new DiemThanhPhan(1,1,7,7,7));
    }

    private void initView(Context context) {
        btnThem = findViewById(R.id.btnThem);
        lvDiem = findViewById(R.id.lvDiem);
        adapterDiem = new AdapterDiem(context,dbHelper.getDiemThanhPhan());
        lvDiem.setAdapter(adapterDiem);

        lvDiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogLuaChon(QLDiemActivity.this,i);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAddMonHoc(QLDiemActivity.this);
            }
        });

    }

    private void showDialogLuaChon(Context context, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bạn muốn");
        builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DiemThanhPhan diemThanhPhan = (DiemThanhPhan) adapterDiem.getItem(i);
                showPopupsua(context,diemThanhPhan);
            }
        });

        builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DiemThanhPhan diemThanhPhan = (DiemThanhPhan) adapterDiem.getItem(i);
                dbHelper.deleteDiem(diemThanhPhan.getID_Diem());
                adapterDiem.setData(dbHelper.getDiemThanhPhan());
                Toast.makeText(context,"Xoa thanh cong",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        // Hiển thị Dialog
        builder.show();
    }

    private void showPopupsua(Context context, DiemThanhPhan diemThanhPhan) {
        int pos1=0;
        int pos2=0;
        ArrayList<SinhVienModel> sinhVienModels = dbHelper.getSV();
        ArrayList<MonHocModel> monHocModels = dbHelper.getMonHoc();


        SinhVienModel sinhVienModel = dbHelper.findSVById(diemThanhPhan.getID_SinhVien());
        MonHocModel monHocModel = dbHelper.findMHById(diemThanhPhan.getID_MonHoc());
        for(int i = 0 ;i< sinhVienModels.size();i++){
            if (sinhVienModel.getID_SV() == sinhVienModels.get(i).getID_SV()){
                pos1 = i;
                break;
            }
        }

        for(int i = 0 ;i< monHocModels.size();i++){
            if (monHocModel.getId() == monHocModels.get(i).getId()){
                pos2 = i;
                break;
            }
        }


        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_diem);
        Spinner spinnerSV = dialog.findViewById(R.id.spSV);
        Spinner spinnerMH = dialog.findViewById(R.id.spMH);
        Button btnThem = dialog.findViewById(R.id.btn);
        btnThem.setText("Sua");
        EditText edDiemCC = dialog.findViewById(R.id.edDiemChuyenCan);
        EditText edDiemBTL = dialog.findViewById(R.id.edDiemBTL);
        EditText edDiemThi= dialog.findViewById(R.id.edDiemThi);
        spinnerSV.setAdapter(new AdapterSpiner<SinhVienModel>(context,dbHelper.getSV()));
        spinnerMH.setAdapter(new AdapterSpiner<MonHocModel>(context,dbHelper.getMonHoc()));

        spinnerSV.setSelection(pos1);
        spinnerMH.setSelection(pos2);
        edDiemCC.setText(diemThanhPhan.getDiemChuyenCan()+"");
        edDiemBTL.setText(diemThanhPhan.getDiemBTL()+"");
        edDiemThi.setText(diemThanhPhan.getDiemThi()+"");
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVienModel sinhVienModel = (SinhVienModel) spinnerSV.getSelectedItem();
                MonHocModel monHocModel = (MonHocModel) spinnerMH.getSelectedItem();
                String diemCC = edDiemCC.getText().toString().trim();
                String diemBTL = edDiemBTL.getText().toString().trim();
                String diemThi= edDiemThi.getText().toString().trim();
                if(diemCC.length()!=0
                        && diemBTL.length()!=0 && diemThi.length()!=0)
                {
                    dbHelper.updateDiem(diemThanhPhan.getID_Diem(),new DiemThanhPhan(monHocModel.getId(),sinhVienModel.getID_SV(),Double.parseDouble(diemCC)
                            ,Double.parseDouble(diemBTL),Double.parseDouble(diemThi)));
                    Toast.makeText(context,"Sua Thanh cong",Toast.LENGTH_SHORT).show();
                    adapterDiem.setData(dbHelper.getDiemThanhPhan());
                }
                else {
                    Toast.makeText(context,"Vui Long kiem tra lai",Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();

    }


    void showDialogAddMonHoc(Context context){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_diem);
        Spinner spinnerSV = dialog.findViewById(R.id.spSV);
        Spinner spinnerMH = dialog.findViewById(R.id.spMH);
        Button btnThem = dialog.findViewById(R.id.btn);

        EditText edDiemCC = dialog.findViewById(R.id.edDiemChuyenCan);
        EditText edDiemBTL = dialog.findViewById(R.id.edDiemBTL);
        EditText edDiemThi= dialog.findViewById(R.id.edDiemThi);

        spinnerSV.setAdapter(new AdapterSpiner<SinhVienModel>(context,dbHelper.getSV()));
        spinnerMH.setAdapter(new AdapterSpiner<MonHocModel>(context,dbHelper.getMonHoc()));

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVienModel sinhVienModel = (SinhVienModel) spinnerSV.getSelectedItem();
                MonHocModel monHocModel = (MonHocModel) spinnerMH.getSelectedItem();
                String diemCC = edDiemCC.getText().toString().trim();
                String diemBTL = edDiemBTL.getText().toString().trim();
                String diemThi= edDiemThi.getText().toString().trim();
                if(diemCC.length()!=0
                && diemBTL.length()!=0 && diemThi.length()!=0)
                {
                    dbHelper.addDiem(new DiemThanhPhan(monHocModel.getId(),sinhVienModel.getID_SV(),Double.parseDouble(diemCC)
                            ,Double.parseDouble(diemBTL),Double.parseDouble(diemThi)));
                    Toast.makeText(context,"Them Thanh cong",Toast.LENGTH_SHORT).show();
                    adapterDiem.setData(dbHelper.getDiemThanhPhan());
                }
                else {
                    Toast.makeText(context,"Vui Long kiem tra lai",Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();

    }

}