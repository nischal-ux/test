package com.example.attack;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

public class UserProfile extends Fragment {
    ListView listView;
    arkoadapter adapter;
    AutoCompleteTextView auto;
    public static ArrayList<info> profile1 = new ArrayList<>();
    info employee;
    String url = "http://192.168.100.58/data/fetch.php";
    FloatingActionButton floata;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.userprofile,null);
        floata=view.findViewById(R.id.floata);
        auto=view.findViewById(R.id.auto);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
//        Intent intent= new Intent(getContext(), Service.class);
//        getContext().startService(intent);
        //ids=getActivity().getIntent().getStringExtra("id");
        // actionBar.setTitle("Art");
        floata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getActivity(),Add_Data_Activity.class)

                );
            }
        });
        listView =view.findViewById(R.id.myListView);
        adapter = new arkoadapter(getContext(),profile1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

//                startActivity(new Intent(getContext(),Detail.class)
//                        .putExtra("position",position));



            }
        });



        retrieveData();

        return view;

    }




    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response","response"+response);
                        profile1.clear();
                        try{

                            JSONArray jsonArray = new JSONArray(response);




                            for(int i=1;i<jsonArray.length();i++){

                                JSONObject object = jsonArray.getJSONObject(i);

                                String id = object.getString("id");
                                String name = object.getString("name");
                                String username = object.getString("username");
                                    String email = object.getString("email");
//                                    String password = object.getString("password");
//                                String contact = object.getString("contact");
                                //String image = object.getString("image");

                                //String conatact = object.getString("contact");
                                //String image = object.getString("contact");
                                //String profile = object.getString("contact");
//                                String description = object.getString("description");
//                                String price = object.getString("price");
//                                String date = object.getString("date");
//
                                String imageurl = object.getString("profile");
                                String url = "http://192.168.100.58/data/Profile/"+imageurl;

//                                String imageurl1 = object.getString("pictures");
//                                String url1 = "http://10.0.2.2/data/Images/"+imageurl1;
                                employee = new info(id,name,username,email,url);
                                profile1.add(employee);
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
