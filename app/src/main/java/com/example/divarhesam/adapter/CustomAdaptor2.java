package com.example.divarhesam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.divarhesam.R;
import com.example.divarhesam.Rahnmagozaresh;

import java.util.ArrayList;

public class CustomAdaptor2 extends BaseAdapter {

    private Context context;
    private ArrayList<Rahnmagozaresh> arrayList1;

    public CustomAdaptor2(Context context, ArrayList<Rahnmagozaresh> arrayList1) {
        this.context = context;
        this.arrayList1 = arrayList1;
    }


    @Override
    public int getCount() {
        return arrayList1.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_rahnamagozaresh, null);
        }

        Rahnmagozaresh rahnmagozaresh = arrayList1.get(position);
            TextView tvmatn = convertView.findViewById(R.id.tvmatn);
            ImageView img1 = convertView.findViewById(R.id.img1);
            ImageView img2 = convertView.findViewById(R.id.img2);

            tvmatn.setText(rahnmagozaresh.getMantn());
            img1.setImageResource((rahnmagozaresh.getImgId1()));
            img2.setImageResource((rahnmagozaresh.getImgId2()));
            return convertView;

        }

}
