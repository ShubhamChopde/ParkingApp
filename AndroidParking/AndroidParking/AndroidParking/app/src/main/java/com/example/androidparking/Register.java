package com.example.androidparking;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.appsearch.StorageInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText name;
    private EditText mobile;
    private EditText email;
    private EditText username;
    private EditText password;

    private Button btn_register;
    AwesomeValidation awesomeValidation;

    private static final String url="http://10.0.2.2/api/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_register = findViewById(R.id.btn_register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.name,RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.mobile,"[5-9]{1}[0-9]{9}$",R.string.invalid_mobile);
        awesomeValidation.addValidation(this,R.id.email, Patterns.EMAIL_ADDRESS,R.string.invaid_email);
        awesomeValidation.addValidation(this,R.id.username,RegexTemplate.NOT_EMPTY,R.string.invalid_username);
        awesomeValidation.addValidation(this,R.id.password,".{6,}",R.string.invalid_password);

        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Login.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Register Failed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Register.class));
                }
                register(name.getText().toString(),mobile.getText().toString(),email.getText().toString(),username.getText().toString(),password.getText().toString());
            }

        });
    }
    public void register(final String name, final String mobile, final String email, final String username, final String password)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Register.this, "Register Success!", Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "Register Error! " +error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put( "name",name);
                params.put( "mobile",mobile);
                params.put( "email",email);
                params.put( "username",username);
                params.put( "password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}