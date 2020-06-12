package com.example.attack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity  {
    EditText ed_email,ed_password;
    ImageView ay;
SharedPreferences sharedPreferences;
    String str_email,str_password;
    CheckBox check;
Animation translate,anim;
TextView a,ab;
    public static ArrayList<info> employeeArrayList = new ArrayList<>();
    info employee;

    String url = "http://192.168.100.58/data/Login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_email = findViewById(R.id.ed_email);
        translate=AnimationUtils.loadAnimation(this,R.anim.translate);
        ed_password = findViewById(R.id.ed_password);
       anim= AnimationUtils.loadAnimation(this,R.anim.scale);
        ab=findViewById(R.id.ab);
        getSupportActionBar().hide();
        a=findViewById(R.id.a);
        a.setAnimation(translate);
        ab.setAnimation(translate);
        //ab.startAnimation(translate);
        check=findViewById(R.id.check);
 sharedPreferences=getSharedPreferences("Userinfo",0);

//
        if (sharedPreferences.getBoolean("rememberme", false)) {
            startActivity(new Intent(LoginActivity.this, ViewPager.class));
            finish();

        }

    }

    public void Login(View view) {


        if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else {


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");

            progressDialog.show();

            String registeremail = sharedPreferences.getString("email", "");
            String registerpassword = sharedPreferences.getString("password", "");
            str_email = ed_email.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();

//            if (str_email.equals(registeremail) && str_password.equals(registerpassword)) {

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        if (response.equalsIgnoreCase("Data Matched")) {
                            if(check.isChecked()) {
//
                                sharedPreferences.edit().putBoolean("rememberme", true).apply();}
                                startActivity(new Intent(getApplicationContext(), ViewPager.class));
//                            }




                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", str_email);
                        params.put("password", str_password);
                        return params;

                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(request);
//

            }
        }

    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response","response"+response);
                        employeeArrayList.clear();
                        try{

                            JSONArray jsonArray = new JSONArray(response);




                            for(int i=1;i<jsonArray.length();i++){

                                JSONObject object = jsonArray.getJSONObject(i);

                                String id = object.getString("id");
                                String username = object.getString("username");
                                //String email = object.getString("email");
//                                    String password = object.getString("password");
                                String contact = object.getString("contact");
                                //String image = object.getString("image");

                                //String conatact = object.getString("contact");
                                //String image = object.getString("contact");
                                //String profile = object.getString("contact");
                                String description = object.getString("description");
                                String price = object.getString("price");
                                String date = object.getString("date");


                                String imageurl1 = object.getString("pictures");
                                String url1 = "http://10.0.2.2/data/Images/"+imageurl1;
                                employee = new info(id,  username,contact,price,description,url1,date);
                                employeeArrayList.add(employee);




                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }








                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());
        requestQueue.add(request);




    }
    public void moveToRegistration(View view) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
        finish();
    }
}
