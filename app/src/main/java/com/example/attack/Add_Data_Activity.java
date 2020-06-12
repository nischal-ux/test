package com.example.attack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.attack.MusicFragment.songInfos;

public class Add_Data_Activity extends AppCompatActivity {
    EditText txtEmail,txtContact,txtAddress,txtpassword,Contact;
    int id;
    private static final String CHANNEL_ID ="personal_notification" ;
    private static final int Notification_ID =001 ;
    Button btn_insert;
    ImageView uploadImage;
    String encodedImage;
    public static ArrayList<info> employeeArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__data_);

        id = getIntent().getIntExtra("id", 0);
        Contact    = findViewById(R.id.edtcontact);
        txtEmail    = findViewById(R.id.edtEmail);
        txtContact  = findViewById(R.id.edtprice);
        txtAddress  = findViewById(R.id.edtAddress);
        txtpassword  = findViewById(R.id.password);
        btn_insert = findViewById(R.id.btnInsert);

        uploadImage=findViewById(R.id.imagetobeUpload);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);

            }
        });
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData() {


        final String email = txtEmail.getText().toString().trim();
        final String contact = txtContact.getText().toString().trim();
        final String address = txtAddress.getText().toString().trim();
        final String password = txtpassword.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");


        if(email.isEmpty()){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(contact.isEmpty()){
            Toast.makeText(this, "Enter Contact", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(address.isEmpty()){
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            return;
        }

        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.100.58/data/insert.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data Inserted")){
                                startActivity(new Intent(Add_Data_Activity.this,ViewPager.class));

                                Toast.makeText(Add_Data_Activity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                clicks(getApplicationContext());
                                progressDialog.dismiss();
                            }
                            else{
                                Toast.makeText(Add_Data_Activity.this, response, Toast.LENGTH_SHORT).show();
                                Toast.makeText(Add_Data_Activity.this, "fail", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    StringBuilder sb=new StringBuilder();
                    sb.append("Error");
                    sb.append(error.toString());
                    Log.i("response",sb.toString());
                    Toast.makeText(Add_Data_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();


                    params.put("username",email);
                    params.put("price",contact);
                    params.put("pictures", encodedImage);
                    params.put("description",address);
                    params.put("password",password);
                    params.put("contact",password);
                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(Add_Data_Activity.this);
            requestQueue.add(request);



        }




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){

            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                 Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                uploadImage.setImageBitmap(bitmap);

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void clicks(Context context){
        RemoteViews remoteViews= new RemoteViews(context.getPackageName(),
                R.layout.music_notification
        );





        String s=encodedImage.getBytes().toString();
        String sname=txtEmail.getText().toString();
        //String song=songInfo.get(position).getArtistname().toString();
        //Intent intentplay=new Intent(this,PlayerAcivity.class).setAction(ACTIONPREVIOUS);
        ///PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[]{intentplay}, PendingIntent.FLAG_UPDATE_CURRENT);
        // int previous = R.drawable.ic_skip_previous_black_24dp;

        // Bitmap bitmapFactory=BitmapFactory.decodeResource(context.getResources(),Integer.parseInt(songInfo.get(position).getSongUrl()));
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Add_Data_Activity.this, CHANNEL_ID);
        builder  .setSmallIcon(R.drawable.ic_add_a_photo_black_24dp);
       // builder.setContent(remoteViews);
        // builder.setLargeIcon(s);
        builder.setContentTitle(sname);
        builder.setContentText("ImageUploaded");
        //builder.addAction(previous,"",pendingIntent);
        //builder.setCustomBigContentView(remoteViews);
        //builder  .setContentText(song);
        builder .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(Notification_ID,builder.build());
        Log.i( "clicks: ","notificationManagerCompat"+builder);


    }
    }
