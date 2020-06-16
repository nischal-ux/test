package com.example.attack;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
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
import java.util.List;

public class ArtFragment extends Fragment {
    ListView listView;
    MyAdapter adapter;
    ActionBar actionBar;
    FloatingActionButton floata;
SearchView autoComplete;
AutoComplete auto;
    public static ArrayList<info> employeeArrayList = new ArrayList<>();
    public  ArrayList<String> list =new ArrayList<>();
    info employee;
    String url = "http://192.168.100.58/data/fetchImages.php";
        String ids;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_main,null);
        floata=view.findViewById(R.id.floata);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        Intent intent= new Intent(getContext(), Service.class);
        getContext().startService(intent);
        ids=getActivity().getIntent().getStringExtra("id");
     // actionBar.setTitle("Art");

        floata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getActivity(),Add_Data_Activity.class)

                );
            }
        });
        auto=new AutoComplete(getContext(),retrive());
        autoComplete=view.findViewById(R.id.search1);



        listView =view.findViewById(R.id.myListView);
        adapter = new MyAdapter(getContext(),employeeArrayList);
        setheader();
        listView.setAdapter(adapter);
        autoComplete.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<info> result=new ArrayList<>();
                for(info n:employeeArrayList){
                    if(n.getUsername().contains(newText))

                        result.add(n);
                }

                ((MyAdapter)listView.getAdapter()).update(result);
                return false;
            }
        });

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



public void setheader(){
        View headerview=LayoutInflater.from(getContext()).inflate(R.layout.header,null);
        listView.addHeaderView(headerview);
        TextView about=headerview.findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),About.class));
            }
        });
    TextView help=headerview.findViewById(R.id.help);
    help.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(),Help.class));
        }
    });
    TextView home=headerview.findViewById(R.id.home);
    home.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            retrieveData();
        }
    });
}
    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.GET, url,
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
//
                                    String contact = object.getString("contact");

                                    String description = object.getString("description");
                                    String price = object.getString("price");
                                    String date = object.getString("date");
//
//
                                    String imageurl1 = object.getString("pictures");
                                    String url1 = "http://192.168.100.58/data/Images/"+imageurl1;
                                    employee = new info(id,  username,contact,price,description,url1,date);
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
    public ArrayList<String> retrive(){

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response","response"+response);
                        employeeArrayList.clear();
                        try{

                            JSONArray jsonArray = new JSONArray(response);




                            for(int i=1;i<jsonArray.length();i++){

                                JSONObject object = jsonArray.getJSONObject(i);


                                String username = object.getString("username");
//
                               // employee = new info(username);
                                list.add(username);
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


return list;

    }
    public void btn_add_activity(View view) {

    }


    @Override
    public void onResume() {
        super.onResume();
        retrieveData();
    }

public class custom extends BaseAdapter{
List<info> list;
List<info>listed;
Context context;

    public custom(List<info> list, Context context) {
        this.list = list;
        this.listed = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listed.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view =LayoutInflater.from(getContext()).inflate(R.layout.model,null);
        TextView name=view.findViewById(R.id.name);
        name.setText(listed.get(position).getUsername());
        return null;
    }
}
}
