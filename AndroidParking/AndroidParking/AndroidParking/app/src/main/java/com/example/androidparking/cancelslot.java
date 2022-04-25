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
import com.example.androidparking.ui.home.HomeFragment;

import java.util.HashMap;
import java.util.Map;

public class cancelslot extends AppCompatActivity {

    private EditText user_name;

    private Button btn_login;

    private static final String url="http://10.0.2.2/api/delete.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelslot);

        user_name = findViewById(R.id.user_name);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cancelslot(user_name.getText().toString());
            }
        });
    }
    public void cancelslot(String user_name) {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("1")) {
                    Toast.makeText(getApplicationContext(),"Successfully Canceled", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else{
                    //startActivity(new Intent(getApplicationContext(), cancelslot.class));
                    Toast.makeText(getApplicationContext(), "Enter Correct username" , Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(cancelslot.this,"Error" +error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put( "user_name",user_name);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
}