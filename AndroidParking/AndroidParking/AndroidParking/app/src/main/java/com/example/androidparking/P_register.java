package com.example.androidparking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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


import java.util.HashMap;
import java.util.Map;
public class P_register extends AppCompatActivity{
    private EditText name, mobile, email, password, address, slots;
    private Button btn_register;
    AwesomeValidation awesomeValidation;
    private static final String url="http://10.0.2.2/api/p_register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_register);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        slots = findViewById(R.id.slots);
        btn_register = findViewById(R.id.btn_register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.mobile,"[5-9]{1}[0-9]{9}$",R.string.invalid_mobile);
        awesomeValidation.addValidation(this,R.id.email, Patterns.EMAIL_ADDRESS,R.string.invaid_email);
        awesomeValidation.addValidation(this,R.id.password,".{6,}",R.string.invalid_password);
        awesomeValidation.addValidation(this,R.id.address,RegexTemplate.NOT_EMPTY,R.string.invalid_address);
        awesomeValidation.addValidation(this,R.id.slots,RegexTemplate.NOT_EMPTY,R.string.invalid_slots);

        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(P_register.this,P_login.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Register Failed", Toast.LENGTH_SHORT).show();

                }
                P_register(name.getText().toString(),mobile.getText().toString(),email.getText().toString(),password.getText().toString(),address.getText().toString(),slots.getText().toString());
            }

        });
    }


    public void P_register(final String name, final String mobile, final String email, final String password, final String address, final String slots)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if(response.contains("1")) {
                    Toast.makeText(getApplicationContext(), "Register Successful" + response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), P_login.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Enter the Details" , Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(P_register.this, "Register Error! " +error.toString(), Toast.LENGTH_SHORT).show();

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
                params.put( "password",password);
                params.put( "address",address);
                params.put( "slots",slots);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}