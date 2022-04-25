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

import java.util.HashMap;
import java.util.Map;

public class Forgotapassword extends AppCompatActivity {

    private EditText Mailid;
    private EditText password;

    private Button btn_submit;

    private static final String url="http://10.0.2.2/api/update1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotapassword);

        Mailid = findViewById(R.id.Mailid);
        password = findViewById(R.id.password);
        btn_submit = findViewById(R.id.btn_submit);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Forgotapassword(Mailid.getText().toString(),password.getText().toString());
            }
        });
    }

    public void Forgotapassword (String Mailid, String password) {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("1")) {
                    Toast.makeText(getApplicationContext(), "Successfull Changed:", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Enter Correct Mailid" , Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Forgotapassword.class));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Forgotapassword.this,"Error" +error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put( "Mailid",Mailid);
                params.put( "password",password);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
    //   public void fetchdata(View view){startActivity(new Intent(Login.this,Home.class));}




}