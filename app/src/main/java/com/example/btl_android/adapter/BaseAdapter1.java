package com.example.btl_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.btl_android.R;
import com.example.btl_android.model.MonHocModel;
import com.example.btl_android.model.SinhVienModel;

import java.util.ArrayList;

public class BaseAdapter1<T> extends android.widget.BaseAdapter {

    Context context;
    ArrayList<T> list;

    public BaseAdapter1(Context context, ArrayList<T> list) {
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
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item,null);

        TextView tv1 = view.findViewById(R.id.tv1);
        TextView tv2 = view.findViewById(R.id.tv2);
        TextView tv3 = view.findViewById(R.id.tv3);
        TextView tv4 = view.findViewById(R.id.tv4);

        if(list.get(i) instanceof SinhVienModel){
            SinhVienModel sv = (SinhVienModel) list.get(i);
            tv1.setText( "Mã sinh viên: SV"+sv.getID_SV());
            tv2.setText( "Họ và tên: " + sv.getHoTen());
            tv3.setText( "Quê quán: "+sv.getQueQuan());
            tv4.setText( "Năm sinh: "+sv.getNamSinh()+"");
        }

        if(list.get(i) instanceof MonHocModel){
            MonHocModel monHocModel = (MonHocModel) list.get(i);
            tv1.setText( "Mã môn học: MH"+monHocModel.getId());
            tv2.setText( "Tên môn học: " + monHocModel.getTenMonHoc());
            tv3.setText( "Số tín: "+monHocModel.getSoTin() +" tín chỉ");
            tv4.setVisibility(View.GONE);
        }
        return view;
    }

    public void setData(ArrayList<T> arrayList){
        this.list = arrayList;
        notifyDataSetChanged();
    }
}
