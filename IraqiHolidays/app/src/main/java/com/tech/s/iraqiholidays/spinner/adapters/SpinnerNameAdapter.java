package com.tech.s.iraqiholidays.spinner.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tech.s.iraqiholidays.ChangeTypeface;
import com.tech.s.iraqiholidays.model.Hotels;

import java.util.List;

public class SpinnerNameAdapter extends ArrayAdapter<Hotels> {
    private Context context;
    private List<Hotels> list;

    public SpinnerNameAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Hotels> list) {
        super(context, resource, list);
        this.context = context;
        this.list = list;
    }

    public int getCount(){
        return list.size();
    }

    public Hotels getItem(int position){
        return list.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setPadding(15, 15, 15, 15);
        ChangeTypeface.setTypeface(context, label);
        label.setText(list.get(position).getHotelName());
        return label;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setPadding(15, 15, 15, 15);
        ChangeTypeface.setTypeface(context, label);
        label.setText(list.get(position).getHotelName());
        return label;
    }
}
