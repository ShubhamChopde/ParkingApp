package com.example.androidparking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<P_view> {

    Context context;
    List<P_view> arrayListP_view;

    public MyAdapter(@NonNull Context context, List<P_view> arrayListP_view) {
        super(context, R.layout.custom_list_item, arrayListP_view);

        this.context = context;
        this.arrayListP_view = arrayListP_view;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);
        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);

        tvID.setText(arrayListP_view.get(position).getId());
        tvName.setText(arrayListP_view.get(position).getName());

        return view;
    }
}
