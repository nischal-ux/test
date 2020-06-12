package com.example.attack;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<info>  {

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
        TextView price = view.findViewById(R.id.price);
        TextView description = view.findViewById(R.id.description);
        TextView contact = view.findViewById(R.id.contact);
        ImageView image=view.findViewById(R.id.image);

    price.setText(arrayListEmployee.get(position).getPrice());
        username.setText(arrayListEmployee.get(position).getUsername());
        date.setText(arrayListEmployee.get(position).getDate());
    description.setText(arrayListEmployee.get(position).getDescription());
        contact.setText(arrayListEmployee.get(position).getContact());
    Glide.with(context).load(arrayListEmployee.get(position).getPictures()).into(image);



        return view;
    }

    public void update(ArrayList<info> result) {

        arrayListEmployee=new ArrayList<>();
        arrayListEmployee.addAll(result);
        notifyDataSetChanged();
    }
}