package com.example.androidparking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.HashMap;
import java.util.Map;

public class P_login extends AppCompatActivity {
    private EditText email;
    private EditText password;

    private Button btn_login;
    private Button btn_register;
    private Button P_forgetpassword;
    AwesomeValidation awesomeValidation;

    private static final String url="http://10.0.2.2/api/p_login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        P_forgetpassword = findViewById(R.id.P_forgetpassword);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.email, Patterns.EMAIL_ADDRESS,R.string.invaid_email);
        awesomeValidation.addValidation(this,R.id.password,".{6,}",R.string.invalid_password);


        P_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     GlobalVariable.name = username.getText().toString();
                Intent intent = new Intent(P_login.this,P_Forgotapassword.class);
                startActivity(intent);
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(P_login.this, P_register.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // if(awesomeValidation.validate()){
               //     Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                    P_login(email.getText().toString(),password.getText().toString());
                //    startActivity(new Intent(P_login.this,P_profile.class));
              //  }else{
                //    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();

                }

       //         P_login(email.getText().toString(),password.getText().toString());
      //      }
        });
    }
    public void P_login(String email, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if(response.contains("1")) {
                    Toast.makeText(getApplicationContext(), "Login Successful" + response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), P_profile.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Enter the correct Details" , Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(P_login.this,"Error" +error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put( "email",email);
                params.put( "password",password);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }

}