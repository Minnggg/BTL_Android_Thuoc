package com.example.btl_android.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.btl_android.R;
import com.example.btl_android.model.MonHocModel;
import com.example.btl_android.model.SinhVienModel;

import java.util.ArrayList;

public class AdapterSpiner<T> extends ArrayAdapter<T> {
    ArrayList<T> list;

    public AdapterSpiner(@NonNull Context context, ArrayList<T> list) {
        super(context, R.layout.item_spiner, list);
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_spiner, parent, false);

        TextView textView = rowView.findViewById(R.id.tvName);
        if(list.get(position) instanceof SinhVienModel){
            SinhVienModel sinhVienModel = (SinhVienModel) list.get(position);
            textView.setText(sinhVienModel.getHoTen());
        }
        else {
            MonHocModel monHocModel = (MonHocModel) list.get(position);
            textView.setText(monHocModel.getTenMonHoc());
        }

        return rowView;
    }
}
