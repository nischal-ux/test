package com.example.attack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter adapter;
    public static ArrayList<info> employeeArrayList = new ArrayList<>();
    String url = "http://10.0.2.2/data/fetchImages.php";
    info employee;
    long albumId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.myListView);
        adapter = new MyAdapter(this,employeeArrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                startActivity(new Intent(getApplicationContext(),Detail.class)
                        .putExtra("position",position));



            }
        });

       retrieveData();


    }

    @Override
    protected void onResume() {
        super.onResume();
      //  retrieveData();
    }

    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://arsltechmysql.000webhostapp.com/delete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


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




                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);
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
                                    String url1 = "http://10.0.2.2/fata/Images/"+imageurl1;
                                    employee = new info(id,  name,  email, password,contact,price,description,url);
                                    employeeArrayList.add(employee);
                                    adapter.notifyDataSetChanged();



                                }



                            } catch (JSONException ex) {
                            ex.printStackTrace();
                        }








                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }
    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Add_Data_Activity.class));
    }

}

