package com.example.androidparking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidparking.databinding.ActivityForgotapasswordBinding;
import com.example.androidparking.ui.home.HomeFragment;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText password;

    private Button btn_login;
    private Button btn_register;
    private Button forgetpassword;

    private static final String url="http://10.0.2.2/api/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        forgetpassword = findViewById(R.id.forgetpassword);

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     GlobalVariable.name = username.getText().toString();
                Intent intent = new Intent(Login.this,Forgotapassword.class);
                startActivity(intent);
            }
        });





        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     GlobalVariable.name = username.getText().toString();
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login(username.getText().toString(),password.getText().toString());
            }
        });
    }
    public void login(String username, String password) {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("1")) {
                    Toast.makeText(getApplicationContext(), "Successful Login:", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Enter Correct username" , Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this,"Error" +error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put( "username",username);
                params.put( "password",password);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
 //   public void fetchdata(View view){startActivity(new Intent(Login.this,Home.class));}
    public void P_login(View view){startActivity(new Intent(Login.this,P_login.class));}

}