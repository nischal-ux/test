package com.example.attack;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends ArrayAdapter<info> {

    Context context;
    List<info> arrayListEmployee;


    public MyAdapter(@NonNull Context context, List<info> arrayListEmployee) {
        super(context,0,arrayListEmployee);

        this.context = context;
        this.arrayListEmployee = arrayListEmployee;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);
        TextView username=view.findViewById(R.id.username);
        TextView date=view.findViewById(R.id.date);
        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);
        TextView time = view.findViewById(R.id.time);
        ImageView image=view.findViewById(R.id.image);
        ImageView profile=view.findViewById(R.id.profile);
    tvID.setText(arrayListEmployee.get(position).getId());
        username.setText(arrayListEmployee.get(position).getName());
        date.setText(arrayListEmployee.get(position).getDate());
    tvName.setText(arrayListEmployee.get(position).getPassword());
    Glide.with(context).load(arrayListEmployee.get(position).getProfileurl()).into(profile);
        Glide.with(context).load(arrayListEmployee.get(position).getPictures()).into(image);


        return view;
    }
}