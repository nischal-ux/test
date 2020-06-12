package com.example.attack;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AutoComplete extends ArrayAdapter<String>  {

     Context context;

    public AutoComplete(@NonNull Context context, ArrayList<String> list) {
        super(context, 0,list);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView name=new TextView(context);
        final String info=getItem(position);
        name.setText(info);

        name.setPadding(15,15,5,15);

//        name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getContext().startActivity(new Intent(getContext(),RegistrationActivity.class));
//            }
//        });
        return name;

    }
}
