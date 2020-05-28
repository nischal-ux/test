package com.example.attack;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class Music extends Fragment {
    ListView songlist;
    String[]items;
    ImageView smg;
    private ArrayList<SongInfo> songInfos = new ArrayList<SongInfo>();;
ArrayAdapter<String> adp;
    SearchView searchView;
    ActionBar actionBar;
    FloatingActionButton floata;
    private static final String CHANNEL_ID ="personal_notification" ;
    private static final int Notification_ID =001 ;
    private static final String ACTIONPREVIOUS="action_previous";
    private static final String ACTION_NEXT="action_next";
    private static final String ACTION_PLAY="action_play";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.songlist,null);
        songlist=view.findViewById(R.id.songlist);
        //songlist.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        floata=view.findViewById(R.id.floa);
        // actionBar.setTitle("Art");
        floata.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (searchView.getVisibility()==View.VISIBLE){
                    searchView.setVisibility(View.GONE);

                }else{

                    searchView.setVisibility(View.VISIBLE);
                }


            }


        });
        searchView=view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
               //adp.getFilter().filter(s);
                return false;
            }
        });
        searchView.setVisibility(View.GONE);
//        actionBar.setTitle("Music");
        runtime();



        return view;
    }
    public void runtime() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                       // display();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    public ArrayList<File> findSong(File root){
        ArrayList<File> at = new ArrayList<File>();
        File[] files = root.listFiles();
        for(File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                at.addAll(findSong(singleFile));
            }
            else{
                if(singleFile.getName().endsWith(".mp3") ||
                        singleFile.getName().endsWith(".wav")){
                    at.add(singleFile);
                }
            }
        }
        return at;
    }
    void display(){
        final ArrayList<File> mySongs = findSong(Environment.getExternalStorageDirectory());
        items = new String[ mySongs.size() ];
        for(int i=0;i<mySongs.size();i++){
            //toast(mySongs.get(i).getName().toString());
            items[i] = mySongs.get(i).getName().toString().replace(".mp3","").replace(".wav","");
        }
        ArrayAdapter<String> adp = new
                ArrayAdapter<String>(getContext(),R.layout.playlist_items,R.id.title,items);
        songlist.setAdapter(adp);


        songlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int
                    position, long l) {

                String songName = songlist.getItemAtPosition(position).toString();
                startActivity(new Intent(getContext(),PlayerAcivity.class)

                        .putExtra("pos",position).putExtra("songs",mySongs).putExtra("songname",songName));
            }
        });
    }



    //final ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(getContext(),R.layout.playlist_items,R.id.title,items);









    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.menu1:
                searchView.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "click1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(getContext(), "click2", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    public void clicks(Context context){
        RemoteViews remoteViews= new RemoteViews(context.getPackageName(),
                R.layout.music_notification
        );
        //Intent intentplay=new Intent(this,PlayerAcivity.class).setAction(ACTIONPREVIOUS);
        ///PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intentplay}, PendingIntent.FLAG_UPDATE_CURRENT);
        // int previous = R.drawable.ic_skip_previous_black_24dp;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_ID);
        builder  .setSmallIcon(R.drawable.ic_search_black_24dp);
        builder  .setContentTitle("hjb");
        //builder.addAction(previous,"",pendingIntent);
        builder.setCustomBigContentView(remoteViews);
        builder  .setContentText("this is content");
        builder .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(Notification_ID,builder.build());
        Log.i( "clicks: ","notificationManagerCompat"+builder);


    }
}


