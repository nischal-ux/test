package com.example.attack;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ArtFragment extends Fragment {
    ListView listView;
    MyAdapter adapter;
    ActionBar actionBar;
    FloatingActionButton floata;
    public static ArrayList<info> employeeArrayList = new ArrayList<>();
    info employee;
    String url = "http://10.0.2.2/data/fetchImages.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main,null);
        floata=view.findViewById(R.id.floata);
     // actionBar.setTitle("Art");
        floata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Add_Data_Activity.class));
            }
        });
        listView =view.findViewById(R.id.myListView);
        adapter = new MyAdapter(getContext(),employeeArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                startActivity(new Intent(getContext(),Detail.class)
                        .putExtra("position",position));



            }
        });



        retrieveData();

        return view;

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
                                    employee = new info(id,  name,  email, password,contact,price,description,url,url1,date);
                                    employeeArrayList.add(employee);
                                    adapter.notifyDataSetChanged();



                                }



                            } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }








                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);




    }
    public void btn_add_activity(View view) {

    }


    @Override
    public void onResume() {
        super.onResume();
        retrieveData();
    }


}
