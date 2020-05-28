package com.example.attack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    EditText txtName,txtEmail,txtContact,txtAddress;
    Button btn_insert;
    ImageView uploadImage;
SharedPreferences sharedPreferences;
    String encodedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txtName     = findViewById(R.id.username);
        txtEmail    = findViewById(R.id.email);
        txtContact  = findViewById(R.id.Contact);
        txtAddress  = findViewById(R.id.pass);
        btn_insert = findViewById(R.id.btn_register);
        uploadImage=findViewById(R.id.imageUpload);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);

            }
        });
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData() {

        final String name = txtName.getText().toString().trim();
        final String email = txtEmail.getText().toString().trim();
        final String password = txtContact.getText().toString().trim();
        final String contact = txtAddress.getText().toString().trim();
        SharedPreferences sharedPreferences=getSharedPreferences("Userinfo",0);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("email",email);
        editor.putString("password",password);
        editor.apply();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(name.isEmpty()){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(email.isEmpty()){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(contact.isEmpty()){
            Toast.makeText(this, "Enter Contact", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(password.isEmpty()){
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            return;
        }

        else{

            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.100.58/data/insert.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                                Toast.makeText(RegistrationActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegistrationActivity.this, "fail", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegistrationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("name",name);
                    params.put("email",email);
                    params.put("password",password);
                    params.put("contact",contact);
                    params.put("profileurl", encodedImage);
                    //params.put("description",address);
                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(RegistrationActivity.this);
            requestQueue.add(request);



        }




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){

            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                uploadImage.setImageBitmap(bitmap);

                imageStore(bitmap);


            } catch ( FileNotFoundException e) {
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void imageStore(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
