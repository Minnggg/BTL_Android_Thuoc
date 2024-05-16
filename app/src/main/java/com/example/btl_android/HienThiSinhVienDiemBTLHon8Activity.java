package com.example.btl_android;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btl_android.adapter.AdapterSpiner;
import com.example.btl_android.adapter.BaseAdapter1;
import com.example.btl_android.model.DiemThanhPhan;
import com.example.btl_android.model.MonHocModel;
import com.example.btl_android.model.SinhVienModel;

import java.util.ArrayList;

public class HienThiSinhVienDiemBTLHon8Activity extends AppCompatActivity {
    Button btnLoc;
    DBHelper dbHelper;
    ListView lvSinhVien;
    Spinner spinnerMH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hien_thi_sinh_vien_diem_btlhon8);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DBHelper(HienThiSinhVienDiemBTLHon8Activity.this);
        initView(HienThiSinhVienDiemBTLHon8Activity.this);
    }
    private void initView(Context context) {
        btnLoc = findViewById(R.id.btnLoc);
        lvSinhVien = findViewById(R.id.lvSinhVien);
        spinnerMH = findViewById(R.id.spMH);
        spinnerMH.setAdapter(new AdapterSpiner<MonHocModel>(context,dbHelper.getMonHoc()));


        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<SinhVienModel> sinhVienModels = new ArrayList<>();
                MonHocModel monHocModel = (MonHocModel) spinnerMH.getSelectedItem();
                ArrayList<DiemThanhPhan> diemThanhPhans = dbHelper.getDiemThanhPhan();
                for(DiemThanhPhan item : diemThanhPhans){
                    if(item.getID_MonHoc()== monHocModel.getId() && item.getDiemBTL() >= 8.0){
                        sinhVienModels.add(dbHelper.findSVById(item.getID_SinhVien()));
                    }
                }
                lvSinhVien.setAdapter(new BaseAdapter1<SinhVienModel>(context,sinhVienModels));
            }
        });
    }
}