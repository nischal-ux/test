package com.example.attack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import static com.example.attack.MusicFragment.songInfos;

public class PlayerAcivity extends AppCompatActivity {
    ImageView next, pause, previous;
    private static final String CHANNEL_ID ="personal_notification" ;
    private static final int Notification_ID =001 ;
    ImageView cover,cov;
    SeekBar seek;

    TextView songlabel,artist;
    static MediaPlayer myMediaPlayer;
    int position;
String artistname;
    String sname;
    static ArrayList<SongInfo> songInfo = new ArrayList<SongInfo>();
    Thread updateseekbar;
    TextView last,Starttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_acivity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Now Playing");
        clicks(getApplicationContext());
       //  Uri uri=Uri.parse(songInfo.get(position).get());
        cover = findViewById(R.id.cover);
        cov = findViewById(R.id.cov);
        pause = findViewById(R.id.pause);
        Starttime = findViewById(R.id.current);
        last = findViewById(R.id.last);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        artist=findViewById(R.id.artist);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seek.setMax(myMediaPlayer.getDuration());
                if(myMediaPlayer.isPlaying()){
                pause.setBackgroundResource(R.drawable.ic_pause_24dp);
                    myMediaPlayer.pause();

                }
                else {
                    pause.setBackgroundResource(R.drawable.ic_play_arrow_black_24dp);
                    myMediaPlayer.start();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(myMediaPlayer.isPlaying()){
                myMediaPlayer.stop();
                myMediaPlayer.release();
                position = ((position + 1) % songInfo.size());

                sname = songInfo.get(position).getSongname().toString();

                songlabel.setText(sname);
                    artistname = songInfo.get(position).getArtistname().toString();
                    artist.setText(artistname);
                    Uri u = Uri.parse(songInfo.get(position).getSongUrl());
                    // songNameText.setText(getSongName);
                    byte[] image=getAlbumImage(songInfo.get(position).getSongUrl());

                    Glide.with(getApplication()).asBitmap().load(image).into(cover);
                    Glide.with(getApplication()).asBitmap().load(image).into(cov);
                    myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);


                //  myMediaPlayer = MediaPlayer.create(getApplicationContext(),u);
                try {
                    myMediaPlayer.start();
                } catch (Exception e) {

                }

            }else {

                    myMediaPlayer.stop();
                    myMediaPlayer.release();
                    position = ((position + 1) % songInfo.size());

                    sname = songInfo.get(position).getSongname().toString();
                    songlabel.setText(sname);

                    Uri u = Uri.parse(songInfo.get(position).toString());
                    // songNameText.setText(getSongName);
                    myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                }}
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //songNameText.setText(getSongName);
                myMediaPlayer.stop();
                myMediaPlayer.release();

                position=((position-1)<0)?(songInfo.size()-1):(position-1);
                Uri u = Uri.parse(songInfo.get(position).getSongUrl());
                myMediaPlayer = MediaPlayer.create(getApplicationContext(),u);
                sname = songInfo.get(position).getSongname().toString();
                songlabel.setText(sname);
                artistname = songInfo.get(position).getArtistname().toString();
                artist.setText(artistname);
                byte[] image=getAlbumImage(songInfo.get(position).getSongUrl());
                Glide.with(getApplication()).asBitmap().load(image).into(cover);
                Glide.with(getApplication()).asBitmap().load(image).into(cov);
                myMediaPlayer.start();


            }
        });

        seek = findViewById(R.id.seek);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                last.setText(getTimeFormatted(myMediaPlayer.getDuration()));
                Starttime.setText(getTimeFormatted(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                myMediaPlayer.seekTo(seekBar.getProgress());

            }
        });


        songlabel = findViewById(R.id.songlabel);
//
//
        updateseekbar=new Thread(){
            @Override
            public void run() {
                int totalDuration=myMediaPlayer.getDuration();
                int current=1;

                while (current<totalDuration){
                    try {

                        sleep(10000);
                        current=myMediaPlayer.getCurrentPosition();
                        seek.setProgress(current);

                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };


        position=getIntent().getIntExtra("pos",0);

        songInfo=songInfos;

        String songName=songInfo.get(position).getSongname().toString();
  songlabel.setText(songName);
        artistname = songInfo.get(position).getArtistname().toString();
        artist.setText(artistname);
            songlabel.setSelected(true);

        if(songInfo!=null) {
            Uri u = Uri.parse(songInfo.get(position).getSongUrl());
        }

        if (myMediaPlayer!=null){
            myMediaPlayer.stop();
            myMediaPlayer.release();

            Uri u = Uri.parse(songInfo.get(position).getSongUrl());
            myMediaPlayer=MediaPlayer.create(getApplicationContext(),u);
            myMediaPlayer.start();

        } else{
            Uri u = Uri.parse(songInfo.get(position).getSongUrl());
            myMediaPlayer=MediaPlayer.create(getApplicationContext(),u);
            myMediaPlayer.start();
        }
        seek.setMax(myMediaPlayer.getDuration());
        byte[] image=getAlbumImage(songInfo.get(position).getSongUrl());

        Glide.with(this).asBitmap().load(image).into(cover);
        Glide.with(getApplication()).asBitmap().load(image).into(cov);
        updateseekbar.start();



    }
    private String getTimeFormatted(long milliSeconds) {
        String finalTimerString = "";
        String secondsString;

        //Converting total duration into time
        int hours = (int) (milliSeconds / 3600000);
        int minutes = (int) (milliSeconds % 3600000) / 60000;
        int seconds = (int) ((milliSeconds % 3600000) % 60000 / 1000);

        // Adding hours if any
        if (hours > 0)
            finalTimerString = hours + ":";

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10)
            secondsString = "0" + seconds;
        else
            secondsString = "" + seconds;

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // Return timer String;
        return finalTimerString;
    }



        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
        public byte[] getAlbumImage(String uri) {
        android.media.MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(uri.toString());
        byte[] data = mmr.getEmbeddedPicture();

          return data;


    }
    public void clicks(Context context){
        RemoteViews remoteViews= new RemoteViews(context.getPackageName(),
                R.layout.music_notification
        );


        position=getIntent().getIntExtra("pos",0);

        songInfo=songInfos;

        String sname=songInfo.get(position).getSongname().toString();
        String song=songInfo.get(position).getArtistname().toString();
        //Intent intentplay=new Intent(this,PlayerAcivity.class).setAction(ACTIONPREVIOUS);
        ///PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intentplay}, PendingIntent.FLAG_UPDATE_CURRENT);
        // int previous = R.drawable.ic_skip_previous_black_24dp;

       // Bitmap bitmapFactory=BitmapFactory.decodeResource(context.getResources(),Integer.parseInt(songInfo.get(position).getSongUrl()));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(PlayerAcivity.this, CHANNEL_ID);
        builder  .setSmallIcon(R.drawable.ic_play_arrow_black_24dp);
        //builder.setContent(remoteViews);
      //  builder.setLargeIcon(bitmapFactory);
        builder.setContentTitle(sname);

        //builder.addAction(previous,"",pendingIntent);
        //builder.setCustomBigContentView(remoteViews);
        builder  .setContentText(song);
        builder .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(Notification_ID,builder.build());
        Log.i( "clicks: ","notificationManagerCompat"+builder);


    }
}
