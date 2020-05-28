package com.example.attack;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Demo extends AppCompatActivity {
    TextView tvid, tvname, tvemail, tvcontact, tvaddress;
    String url = "http://10.0.2.2/data/fetchImages.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvemail = findViewById(R.id.txtemail);
        tvcontact = findViewById(R.id.txcontact);
        tvaddress = findViewById(R.id.txtaddress);
        retrieveData();
    }


    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response","response"+response);

                        try{



                                JSONObject object = new JSONObject(response);

                                String id = object.getString("id");
                                String name = object.getString("username");
                                String email = object.getString("email");
                                String password = object.getString("password");
                                String contact = object.getString("contact");
                                //String image = object.getString("image");

                                //String conatact = object.getString("contact");
                                //String image = object.getString("contact");
                                //String profile = object.getString("contact");
                                String description = object.getString("description");
                                String price = object.getString("price");
                                String date = object.getString("date");

                                String imageurl = object.getString("profileurl");
                                String url = "http://10.0.2.2/data/Images/"+imageurl;
                                String imageurl1 = object.getString("pictures");
                                String url1 = "http://10.0.2.2/data/Images/"+imageurl1;

                            tvname.setText(name);


                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }




                    }








                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);




    }
}