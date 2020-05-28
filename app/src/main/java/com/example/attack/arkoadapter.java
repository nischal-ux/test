package com.example.attack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class arkoadapter extends ArrayAdapter<info> {
Context context;
List<info> profile;

    public arkoadapter(@NonNull Context context, List<info> profile) {
        super(context, 0,profile);
        this.context=context;
        this.profile=profile;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(context).inflate(R.layout.profile,null,true);


        return view;
    }
}
