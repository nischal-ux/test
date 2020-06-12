package com.example.attack;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

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

public class Service extends android.app.Service {
    public static ArrayList<info> employeeArrayList = new ArrayList<>();
    info employee;
    String url = "http://10.0.2.2/data/Select.php";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        retrieveData();
        super.onCreate();
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
                                String name = object.getString("name");
                                String email = object.getString("email");
                                String password = object.getString("password");


                                employee = new info();
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
}
