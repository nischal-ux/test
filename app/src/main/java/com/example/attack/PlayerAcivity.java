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

public class PlayerAcivity extends AppCompatActivity {

    private static final String CHANNEL_ID ="personal_notification" ;
    private static final int Notification_ID =001 ;
    ImageView next, pause, previous;
    ImageView cover;
    SeekBar seek;
    ArrayList<File> mySongs;
    TextView songlabel;
    static MediaPlayer myMediaPlayer;
    int position;
    String sname;

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
        // Uri uri=Uri.parse(mySongs.get(position).getPath());

        // Glide.with(this).load(mySongs.get(position)).into(cover);
        pause = findViewById(R.id.pause);
        Starttime = findViewById(R.id.current);
        last = findViewById(R.id.last);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seek.setMax(myMediaPlayer.getDuration());
                if (myMediaPlayer.isPlaying()) {

                    myMediaPlayer.pause();

                } else {

                    myMediaPlayer.start();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();
                myMediaPlayer.release();
                position = ((position + 1) % mySongs.size());
                Uri u = Uri.parse(mySongs.get(position).toString());
                // songNameText.setText(getSongName);
                myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);

                sname = mySongs.get(position).getName().toString();
                songlabel.setText(sname);


                try {
                    myMediaPlayer.start();
                } catch (Exception e) {
                }

            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //songNameText.setText(getSongName);
                myMediaPlayer.stop();
                myMediaPlayer.release();

                position = ((position - 1) < 0) ? (mySongs.size() - 1) : (position - 1);
                Uri u = Uri.parse(mySongs.get(position).toString());
                myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                sname = mySongs.get(position).getName().toString();
                songlabel.setText(sname);
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
        cover = findViewById(R.id.cover);

        songlabel = findViewById(R.id.songlabel);


        updateseekbar = new Thread() {
            @Override
            public void run() {
                int totalDuration = myMediaPlayer.getDuration();
                int current = 1;

                while (current < totalDuration) {
                    try {

                        sleep(10000);
                        current = myMediaPlayer.getCurrentPosition();
                        seek.setProgress(current);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        if (myMediaPlayer != null) {
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }

        Intent intent = getIntent();
        Intent i = getIntent();
        Bundle b = i.getExtras();


        mySongs = (ArrayList) b.getParcelableArrayList("songs");

        sname = mySongs.get(position).getName().toString();

        String SongName = i.getStringExtra("songname");
        songlabel.setText(SongName);
        songlabel.setSelected(true);

        position = b.getInt("pos", 0);
        Uri u = Uri.parse(mySongs.get(position).toString());

        myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);
        myMediaPlayer.start();

        seek.setMax(myMediaPlayer.getDuration());
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



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId()==android.R.id.home){
//            onBackPressed();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
private Bitmap getAlbumImage(String path) {
    android.media.MediaMetadataRetriever mmr = new MediaMetadataRetriever();
    mmr.setDataSource(path);
    byte[] data = mmr.getEmbeddedPicture();
    if (data != null) return BitmapFactory.decodeByteArray(data, 0, data.length);
    return null;
}
    public void clicks(Context context){
        RemoteViews remoteViews= new RemoteViews(context.getPackageName(),
                R.layout.music_notification
        );
        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        mySongs=(ArrayList) bundle.getParcelableArrayList("songs");
        sname=mySongs.get(position).getName().toString();
        String songName=i.getStringExtra("songname");
        //Intent intentplay=new Intent(this,PlayerAcivity.class).setAction(ACTIONPREVIOUS);
        ///PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intentplay}, PendingIntent.FLAG_UPDATE_CURRENT);
        // int previous = R.drawable.ic_skip_previous_black_24dp;

        //Bitmap bitmapFactory=BitmapFactory.decodeResource(context.getResources(),);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(PlayerAcivity.this, CHANNEL_ID);
        builder  .setSmallIcon(R.drawable.ic_search_black_24dp);
        //builder.setContent(remoteViews);

        //builder.addAction(previous,"",pendingIntent);
        //builder.setCustomBigContentView(remoteViews);
        builder  .setContentText(songName);
        builder .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(Notification_ID,builder.build());
        Log.i( "clicks: ","notificationManagerCompat"+builder);


    }
}
