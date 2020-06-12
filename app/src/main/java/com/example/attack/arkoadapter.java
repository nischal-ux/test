package com.example.attack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.List;

public class arkoadapter extends ArrayAdapter<info> {
Context context;
List<info> profile1;

    public arkoadapter(@NonNull Context context, List<info> profile1) {
        super(context, 0,profile1);
        this.context=context;
        this.profile1=profile1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile,null,true);

        TextView username = view.findViewById(R.id.price1);
        TextView name = view.findViewById(R.id.description1);
        TextView email = view.findViewById(R.id.contact1);
        ImageView image=view.findViewById(R.id.image1);


        name.setText(profile1.get(position).getName());

        email.setText(profile1.get(position).getEmail());
        username.setText(profile1.get(position).getUsername());
        Glide.with(context).load(profile1.get(position).getProfileurl()).into(image);



        return view;
    }
}
