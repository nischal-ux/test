package com.example.attack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Edit_Activity extends AppCompatActivity {

    EditText edId,edName,edContact,edEmail,edAddress;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);

        edId = findViewById(R.id.ed_id);
        edName = findViewById(R.id.ed_name);
        edContact = findViewById(R.id.ed_contact);
        edEmail = findViewById(R.id.ed_email);
        edAddress = findViewById(R.id.ed_address);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        edId.setText(ArtFragment.employeeArrayList.get(position).getId());
        edName.setText(ArtFragment.employeeArrayList.get(position).getName());
        edEmail.setText(ArtFragment.employeeArrayList.get(position).getEmail());
     //   edContact.setText(MainActivity.employeeArrayList.get(position).getContact());
      //  edAddress.setText(MainActivity.employeeArrayList.get(position).getAddress());




    }

    public void btn_updateData(View view) {
    update( ArtFragment.employeeArrayList.get(position).getId());
    }
    public void update( final String id){

        final String name = edName.getText().toString();
        final String email = edEmail.getText().toString();
        final String contact = edContact.getText().toString();
        final String address = edAddress.getText().toString();







        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/data/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response","response"+response);
                        Toast.makeText(Edit_Activity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),ViewPager.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Edit_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("id",id);
                params.put("description",name);
                params.put("email",email);

                // params.put("contact",contact);
                //params.put("address",address);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Edit_Activity.this);
        requestQueue.add(request);





    }
    }

