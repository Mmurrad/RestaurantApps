package com.example.murad.restaurantapps.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.murad.restaurantapps.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Order_item> {

    Context context;
    ArrayList<Order_item> arrayList;

    public CustomAdapter( @NonNull Context context, ArrayList<Order_item> arrayList) {
        super(context, R.layout.single_listview,arrayList);
        this.context=context;
        this.arrayList=arrayList;
    }

    //@androidx.annotation.NonNull
    @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView, @NonNull ViewGroup parent) {

       LayoutInflater layoutInflater=LayoutInflater.from(context);
       View view=layoutInflater.inflate(R.layout.single_listview,parent,false);

        TextView item=view.findViewById(R.id.item_id);
        TextView qty=view.findViewById(R.id.qty_id);
        TextView prize=view.findViewById(R.id.prize_id);

        item.setText(arrayList.get(position).getItem_name());
        qty.setText(arrayList.get(position).getQty());
        prize.setText(arrayList.get(position).getPrize());

        return view;
    }
}
