package com.example.attack;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Detail extends AppCompatActivity {
    TextView tvid, tvname, tvprice, tvcontact,tvdescription;
    int positions;
    ImageView image;
    Button update, delete;
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;
    String posturl = "http://192.168.100.58/data/up.php/+id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvprice= findViewById(R.id.txtprice);
        tvcontact = findViewById(R.id.txtcontact);
       tvdescription=findViewById(R.id.txtdescription);
        image=findViewById(R.id.image);
        update = findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Edit_Activity.class).
                        putExtra("pos", positions));
            }
        });
        delete = findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog();
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();


        Intent intent = getIntent();
        positions = intent.getExtras().getInt("position");

       Glide.with(this).load(ArtFragment.employeeArrayList.get(positions).getPictures()).into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tvcontact.setText("Contact: "+ArtFragment.employeeArrayList.get(positions).getContact());
        tvdescription.setText("Description: "+ArtFragment.employeeArrayList.get(positions).getDescription());
       tvid.setText("ID: " + ArtFragment.employeeArrayList.get(positions).getId().toString());
       tvname.setText("UserName: " + ArtFragment.employeeArrayList.get(positions).getUsername());
       tvprice.setText("Price: " + ArtFragment.employeeArrayList.get(positions).getPrice());
      }

    public void DeleteData(final String id) {
        this.mRequestQueue = Volley.newRequestQueue(this);
        StringRequest rl = new StringRequest(Request.Method.POST, posturl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("response", posturl);
                startActivity(new Intent(getApplicationContext(), ViewPager.class));
                finish();
                Log.i("response", "response" + response);


//

            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Detail.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                   params.put("id",id);

                return params;
            }
        };
        this.mStringRequest = rl;
        this.mRequestQueue.add(this.mStringRequest);

    }

    public void customDialog() {
        getSupportActionBar().setTitle("DeleteData");
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setTitle("Delete");
        final EditText  password;

         final TextView username;
        Button login;
        View view = LayoutInflater.from(this).inflate(R.layout.custom, null);

        username=view.findViewById(R.id.username);
        username.setText(ArtFragment.employeeArrayList.get(positions).getUsername());
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

                                    Toast.makeText(Detail.this, "Data deleted", Toast.LENGTH_SHORT).show();
                                    DeleteData(ArtFragment.employeeArrayList.get(positions).getId());
                                    startActivity(new Intent(getApplicationContext(),ViewPager.class));
                                } else {
                                    Toast.makeText(Detail.this, "fail", Toast.LENGTH_SHORT).show();

                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Detail.this, error.getMessage(), Toast.LENGTH_SHORT).show();

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


                RequestQueue requestQueue = Volley.newRequestQueue(Detail.this);
                requestQueue.add(request);

            }

        });
        dialog.setContentView(view);
        dialog.show();

    }


}