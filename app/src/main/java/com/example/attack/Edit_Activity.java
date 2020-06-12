package com.example.attack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Edit_Activity extends AppCompatActivity {
ImageView image1;
    private static final String CHANNEL_ID ="personal_notification" ;
    private static final int Notification_ID =001 ;
    EditText edId,edName,edContact,eddescription,edprice;
    int position;
    String encodedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);
        image1=findViewById(R.id.image1);
        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edContact = findViewById(R.id.ed_contact);
        eddescription = findViewById(R.id.ed_description);
        edprice = findViewById(R.id.ed_price);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        position = intent.getExtras().getInt("pos",0);

        Glide.with(this).load(ArtFragment.employeeArrayList.get(position).getPictures()).into(image1);
        edId.setText(ArtFragment.employeeArrayList.get(position).getId());
 edName.setText(ArtFragment.employeeArrayList.get(position).getUsername());
 eddescription.setText(ArtFragment.employeeArrayList.get(position).getDescription());
edContact.setText(ArtFragment.employeeArrayList.get(position).getContact());
    edprice.setText(ArtFragment.employeeArrayList.get(position).getPrice());
//
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);

            }
        });


    }

    public void btn_updateData(View view) {
        customUpdate();
    }
    public void update( final String id){

        final String name = edName.getText().toString();
        final String description = eddescription.getText().toString();
        final String contact = edContact.getText().toString();
        final String price = edprice.getText().toString();







        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.100.58/data/update.php/+id",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response","response"+response);
                        Toast.makeText(Edit_Activity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ViewPager.class));
                        clicks(getApplicationContext());
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Edit_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("id",id);
                params.put("description",description);
                params.put("username",name);
                params.put("pictures",encodedImage);
                params.put("contact",contact);
                params.put("price",price);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Edit_Activity.this);
        requestQueue.add(request);





    }
    public void customUpdate() {

            getSupportActionBar().setTitle("Update Data");
            final BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setTitle("Delete");
            final EditText  password;
            Button login;
            View view = LayoutInflater.from(this).inflate(R.layout.custom, null);
                final ProgressDialog progressBar;
        final ProgressDialog progressDialog = new ProgressDialog(this);
            password = view.findViewById(R.id.password);
            login = view.findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.100.58/data/check.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    if (response.equalsIgnoreCase("Data Matched")) {

                                        progressDialog.setMessage("Updating....");
                                        progressDialog.show();
                                        update( ArtFragment.employeeArrayList.get(position).getId());
                                        startActivity(new Intent(getApplicationContext(),ViewPager.class));
                                    } else {
                                        Toast.makeText(Edit_Activity.this, "fail", Toast.LENGTH_SHORT).show();
                                        progressDialog.setMessage("Password Incorret");
                                        progressDialog.show();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Edit_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<String, String>();



                            params.put("password", password.getText().toString());

                            return params;
                        }
                    };


                    RequestQueue requestQueue = Volley.newRequestQueue(Edit_Activity.this);
                    requestQueue.add(request);



                }





            });
            dialog.setContentView(view);
            dialog.show();



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){

            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                image1.setImageBitmap(bitmap);

                imageStore(bitmap);


            } catch ( FileNotFoundException e) {
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void imageStore(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }
    public void clicks(Context context){
        RemoteViews remoteViews= new RemoteViews(context.getPackageName(),
                R.layout.music_notification
        );





        //String s=encodedImage.getBytes().toString();
        String sname=edName.getText().toString();
        //String song=songInfo.get(position).getArtistname().toString();
        //Intent intentplay=new Intent(this,PlayerAcivity.class).setAction(ACTIONPREVIOUS);
        ///PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intentplay}, PendingIntent.FLAG_UPDATE_CURRENT);
        // int previous = R.drawable.ic_skip_previous_black_24dp;

        // Bitmap bitmapFactory=BitmapFactory.decodeResource(context.getResources(),Integer.parseInt(songInfo.get(position).getSongUrl()));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Edit_Activity.this, CHANNEL_ID);
        builder  .setSmallIcon(R.drawable.ic_add_a_photo_black_24dp);
      //  builder.setContent(remoteViews);
        // builder.setLargeIcon(s);
        builder.setContentTitle(sname);
        builder.setContentText("Edited");
        //builder.addAction(previous,"",pendingIntent);
        //builder.setCustomBigContentView(remoteViews);
        //builder  .setContentText(song);
        builder .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(Notification_ID,builder.build());
        Log.i( "clicks: ","notificationManagerCompat"+builder);


    }
    public void check(){}

}

