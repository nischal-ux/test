package com.example.attack;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
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
        TextView tvSongName,tvSongArtist;
        Button btnAction;

        View myView = LayoutInflater.from(context).inflate(R.layout.row_songs,null,true);
        tvSongName = myView .findViewById(R.id.tvSongName);
        tvSongArtist = myView.findViewById(R.id.tvArtistName);
        final SongInfo s = getItem(position);

                if(s.getSongname().endsWith(".mp3")||
                        s.getSongname().endsWith(".wav")){
                    tvSongName.setText(s.getSongname());
                    tvSongArtist.setText(s.getArtistname());
                    myView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PlayerAcivity.class)
                                    .putExtra("pos",position).putExtra("songs",s.getSongUrl()).putExtra("songname",s.getSongname());
                            context.startActivity(intent);


                        }
                    });
                }

        //tvSongName.setText(s.getSongname());
        //tvSongArtist.setText(s.getArtistname());

        return myView;
    }
}