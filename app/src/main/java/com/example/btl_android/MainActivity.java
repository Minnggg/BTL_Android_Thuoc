package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{
    Button btnQuanLySV;
    Button btnQuanLyMH;
    Button btnQuanLyDiem;
    Button btnHienThiSVtheoMonHoc;
    Button btnHienThiSVCoBTLHon8d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        setOnCliked();
    }

    private void setOnCliked() {
        btnQuanLySV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QLActivity.class);
                intent.putExtra("type","sinhvien");
                startActivity(intent);
            }
        });

        btnQuanLyMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QLActivity.class);
                intent.putExtra("type","monhoc");
                startActivity(intent);
            }
        });

        btnQuanLyDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QLDiemActivity.class);
                startActivity(intent);
            }
        });

        btnHienThiSVtheoMonHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HienThiSinhVienTheoMonHocActivity.class);
                startActivity(intent);
            }
        });

        btnHienThiSVCoBTLHon8d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HienThiSinhVienDiemBTLHon8Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
         btnQuanLySV = findViewById(R.id.QLSinhVien);
         btnQuanLyMH = findViewById(R.id.QLMonHoc);
         btnQuanLyDiem = findViewById(R.id.QLDiem);
         btnHienThiSVtheoMonHoc = findViewById(R.id.hienThiTheoMon);
         btnHienThiSVCoBTLHon8d = findViewById(R.id.BTLhon8);
    }


}