package com.example.attack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import java.io.File;
import java.util.List;

public class Notification extends AppCompatActivity {
    List<File>arraylist;
    Button btn;
    private static final String CHANNEL_ID ="personal_notification" ;
    private static final int Notification_ID =001 ;
    private static final String ACTIONPREVIOUS="action_previous";
    private static final String ACTION_NEXT="action_next";
    private static final String ACTION_PLAY="action_play";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btn=findViewById(R.id.txtx);

    }
    public void clicks(){
        RemoteViews remoteViews= new RemoteViews(getPackageName(),
                R.layout.music_notification
        );
        //Intent intentplay=new Intent(this,PlayerAcivity.class).setAction(ACTIONPREVIOUS);
        ///PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intentplay}, PendingIntent.FLAG_UPDATE_CURRENT);
        // int previous = R.drawable.ic_skip_previous_black_24dp;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Notification.this, CHANNEL_ID);
        builder  .setSmallIcon(R.drawable.ic_search_black_24dp);
        builder  .setContentTitle("hha");
        //builder.addAction(previous,"",pendingIntent);
        builder.setCustomBigContentView(remoteViews);
        builder  .setContentText("this is content");
        builder .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(Notification_ID,builder.build());
        Log.i( "clicks: ","notificationManagerCompat"+builder);


    }
}
