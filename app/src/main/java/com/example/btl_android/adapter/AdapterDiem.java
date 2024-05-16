package com.example.btl_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.btl_android.DBHelper;
import com.example.btl_android.R;
import com.example.btl_android.model.DiemThanhPhan;
import com.example.btl_android.model.MonHocModel;
import com.example.btl_android.model.SinhVienModel;

import java.util.ArrayList;

public class AdapterDiem extends BaseAdapter {
    Context context;
    ArrayList<DiemThanhPhan> list;

    public AdapterDiem(Context context, ArrayList<DiemThanhPhan> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getID_Diem();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DBHelper dbHelper = new DBHelper(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_diem,null);
        DiemThanhPhan diemThanhPhan = list.get(i);

        TextView tvTenSV = view.findViewById(R.id.tvTenSv);
        TextView tvMonHoc = view.findViewById(R.id.tvTenMonHoc);
        TextView tv3 = view.findViewById(R.id.tv3);
        TextView tv4 = view.findViewById(R.id.tv4);
        TextView tv5 = view.findViewById(R.id.tv5);

        SinhVienModel sinhVienModel = dbHelper.findSVById(diemThanhPhan.getID_SinhVien());
        MonHocModel monHocModel = dbHelper.findMHById(diemThanhPhan.getID_MonHoc());
        if(sinhVienModel !=null && monHocModel !=null){
            tvTenSV.setText(sinhVienModel.getHoTen());
            tvMonHoc.setText(monHocModel.getTenMonHoc());
            tv3.setText("Diem chuyen can" + diemThanhPhan.getDiemChuyenCan());
            tv4.setText("Diem BTL" + diemThanhPhan.getDiemBTL()+"");
            tv5.setText("Diem Thi" + diemThanhPhan.getDiemThi()+"");
        }
        else {
//            tvTenSV.setText(sinhVienModel.getHoTen());
//            tvMonHoc.setText(monHocModel.getTenMonHoc());
//            tv3.setText("Diem chuyen can" + diemThanhPhan.getDiemChuyenCan());
//            tv4.setText("Diem BTL" + diemThanhPhan.getDiemBTL()+"");
//            tv5.setText("Diem Thi" + diemThanhPhan.getDiemThi()+"");
        }
        return view;
    }
    public void setData(ArrayList<DiemThanhPhan> list){
        this.list = list;
        notifyDataSetChanged();
    }

}
