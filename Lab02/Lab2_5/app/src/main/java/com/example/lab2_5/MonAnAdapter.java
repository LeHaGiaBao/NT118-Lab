package com.example.lab2_5;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MonAnAdapter extends BaseAdapter {
    private Context context=null;
    private int layout;
    private ArrayList<MonAn> monAnList=null;


    public MonAnAdapter(Context context, int layout, ArrayList<MonAn> monAnList) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
    }
    @Override
    public int getCount(){
        return monAnList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.item_dish, null,
                            false);
        }

        MonAn monan = monAnList.get(position);
        ImageView imgMonan = (ImageView) convertView.findViewById(R.id.imgMonan);
        TextView txtMonan = (TextView) convertView.findViewById(R.id.txtMonan);
        txtMonan.setSelected(true);
        ImageView icnStar = (ImageView) convertView.findViewById(R.id.icnStar);

        if (monan.isPromotion())
        {
            icnStar.setVisibility(View.VISIBLE);

        }
        else
        {
            icnStar.setVisibility(View.GONE);
        }

        imgMonan.setImageResource(monan.getThumbnail());
        txtMonan.setText(monan.getName());
        return convertView;
    }
}
