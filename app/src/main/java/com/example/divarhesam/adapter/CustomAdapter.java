package com.example.divarhesam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.divarhesam.entity.Proudact;
import com.example.divarhesam.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter implements Filterable {

    private Context context;
    private ArrayList<Proudact> arrayList;
    private ArrayList<Proudact> temp;


    public CustomAdapter(Context context, ArrayList<Proudact> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.temp = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview, null);
        }

        Proudact proudact=  arrayList.get(position);

        TextView textViewname = convertView.findViewById(R.id.name);
        TextView textViewvalue = convertView.findViewById(R.id.value);
        TextView textViewtime = convertView.findViewById(R.id.time);
        ImageView imageView = convertView.findViewById(R.id.img);

        textViewname.setText(proudact.getName());
        textViewvalue.setText(proudact.getValue());
        textViewtime.setText(proudact.getTime());
        imageView.setImageResource(proudact.getImgId());


        return convertView;
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence fiterRequest) {
                FilterResults filterResults = new FilterResults();

                ArrayList<Proudact> filterlist= new ArrayList<>();
                for(Proudact item : temp){
                    if(item.getName().contains(fiterRequest)){
                        filterlist.add(item);
                    }

                }

                filterResults.values=filterlist;
                filterResults.count=filterlist.size();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                arrayList= (ArrayList<Proudact>) results.values;
                notifyDataSetChanged();


            }
        };
    }
}
