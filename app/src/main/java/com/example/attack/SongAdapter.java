package com.example.attack;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends ArrayAdapter<SongInfo> {
    Context context;
    List<SongInfo> songInfos;
    public SongAdapter(@NonNull Context context, List<SongInfo>songInfos) {
        super(context, 0,songInfos);
        this.songInfos=songInfos;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView tvSongName,tvSongArtist,album;
        Button btnAction;
        ImageView cover;
        View myView = LayoutInflater.from(context).inflate(R.layout.row_songs,null,true);
        tvSongName = myView .findViewById(R.id.tvSongName);
        tvSongArtist = myView.findViewById(R.id.tvArtistName);
        album = myView.findViewById(R.id.album);
        cover=myView.findViewById(R.id.cover);


        final SongInfo s = getItem(position);

                if(songInfos.get(position).getSongname().endsWith(".mp3")||
                        songInfos.get(position).getSongname().endsWith(".wav")){
                    tvSongName.setText(songInfos.get(position).getSongname());
                    tvSongArtist.setText(songInfos.get(position).getArtistname());
                    album.setText(s.getAlbum());
                    byte[] image=album(s.getSongUrl());

                        Glide.with(myView).asBitmap().load(image).into(cover);

                }

        //tvSongName.setText(s.getSongname());
        //tvSongArtist.setText(s.getArtistname());

        return myView;
    }

    private byte[] album(String uri){
        MediaMetadataRetriever retriever=new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art=retriever.getEmbeddedPicture();
        retriever.release();
        return art;


    }
    public void update(ArrayList<SongInfo> result) {

        songInfos=new ArrayList<>();
        songInfos.addAll(result);
        notifyDataSetChanged();
    }

}