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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Detail extends AppCompatActivity {
    TextView tvid, tvname, tvemail, tvcontact, tvaddress;
    int position;
    ImageView image;
    Button update, delete;
    private StringRequest mStringRequest;
    private RequestQueue mRequestQueue;
    String posturl = "http://10.0.2.2/data/Delete.php/+id";
    public static ArrayList<info> employeeArrayList = new ArrayList<>();
    info employee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvemail = findViewById(R.id.txtemail);
        tvcontact = findViewById(R.id.txcontact);
        tvaddress = findViewById(R.id.txtaddress);
        image=findViewById(R.id.image);
        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customUpdate();
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
        position = intent.getExtras().getInt("position");

       Glide.with(this).load(ArtFragment.employeeArrayList.get(position).getProfileurl()).into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tvcontact.setText("Contact: "+ArtFragment.employeeArrayList.get(position).getContact());
        tvaddress.setText("Address: "+ArtFragment.employeeArrayList.get(position).getDescription());
       tvid.setText("ID: " + ArtFragment.employeeArrayList.get(position).getId().toString());
       tvname.setText("Name: " + ArtFragment.employeeArrayList.get(position).getName());
       tvemail.setText("Email: " + ArtFragment.employeeArrayList.get(position).getEmail());
      }

    public void DeleteData() {
        this.mRequestQueue = Volley.newRequestQueue(this);
        StringRequest rl = new StringRequest(Request.Method.DELETE, posturl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("response", posturl);
                startActivity(new Intent(getApplicationContext(), ViewPager.class));
                finish();
                Log.i("response", "response" + response);


//
                try {

                    JSONArray jsonArray = new JSONArray(response);


                    for (int i = 1; i < jsonArray.length(); i++) {

                        JSONObject object = new JSONObject(response);

                        String id = object.getString("id");
//
                        employee = new info(id);
                        employeeArrayList.add(employee);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
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
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                   params.put("id",ArtFragment.employeeArrayList.get(position).getId());

                return params;
            }
        };
        this.mStringRequest = rl;
        this.mRequestQueue.add(this.mStringRequest);

    }

    public void customDialog() {
        getSupportActionBar().setTitle("DeleteData");
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Delete");
        final EditText username, password;
        Button login;
        View view = LayoutInflater.from(this).inflate(R.layout.custom, null);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (username.getText().toString().equals(ArtFragment.employeeArrayList.get(position).getEmail()) &&
//                        password.getText().toString().equals(ArtFragment.employeeArrayList.get(position).getPassword())
//                ) {
////                    StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.100.58/music_art/insert.php",
//                            new Response.Listener<String>() {
//                                @Override
//                                public void onResponse(String response) {
//
//                                    if (response.equalsIgnoreCase("Data Inserted")) {
//                                        Toast.makeText(Detail.this, "Data Inserted", Toast.LENGTH_SHORT).show();
//
//                                    } else {
//                                        Toast.makeText(Detail.this, response, Toast.LENGTH_SHORT).show();
//
//                                    }
//
//                                }
//                            }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(Detail.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//
//                    ) {
//                        @Override
//                        protected Map<String, String> getParams() throws AuthFailureError {
//
//                            Map<String, String> params = new HashMap<String, String>();
//
//
//                            params.put("email", username.getText().toString());
//                            params.put("password", password.getText().toString());
//
//                            return params;
//                        }
//                    };
//
//
//                    RequestQueue requestQueue = Volley.newRequestQueue(Detail.this);
//                    requestQueue.add(request);
//
//
//                }
                    DeleteData();
//                }else {
                    Toast.makeText(Detail.this, "Delete", Toast.LENGTH_SHORT).show();
//                }


            }

        });
        dialog.setContentView(view);
        dialog.show();

    }

    public void customUpdate() {
        {
            getSupportActionBar().setTitle("DeleteData");
            Dialog dialog = new Dialog(this);
            dialog.setTitle("Delete");
            final EditText username, password;
            Button login;
            View view = LayoutInflater.from(this).inflate(R.layout.custom, null);

            username = view.findViewById(R.id.username);
            password = view.findViewById(R.id.password);
            login = view.findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (username.getText().toString().equals(ArtFragment.employeeArrayList.get(position).getEmail()) &&
//                            password.getText().toString().equals(ArtFragment.employeeArrayList.get(position).getPassword())
//                    ) {
//
                        startActivity(new Intent(getApplicationContext(), Edit_Activity.class).
                                putExtra("postion", position));
//                    } else {
//                        Toast.makeText(Detail.this, "fail", Toast.LENGTH_SHORT).show();
//                    }


                }

            });
            dialog.setContentView(view);
            dialog.show();


        }
    }}