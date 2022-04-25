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

public class P_AdminFetch extends AppCompatActivity {

    private EditText user_id;

    private Button btn_login;

    private static final String url="http://10.0.2.2/api/p_retrieve.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_adminfetch);

        user_id = findViewById(R.id.user_id);
        btn_login = findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adminbookingfetch(user_id.getText().toString());
            }
        });
    }
    public void adminbookingfetch(String user_id) {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //if(response.contains("1")) {
                    //Toast.makeText(getApplicationContext(), "Successful Login:", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), P_retreive.class));
                //}
                //else{
                  //  Toast.makeText(getApplicationContext(), "Enter Correct username" , Toast.LENGTH_SHORT).show();
                //}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(P_AdminFetch.this,"Error" +error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put( "user_id",user_id);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
    //   public void fetchdata(View view){startActivity(new Intent(Login.this,Home.class));}
 //   public void P_login(View view){startActivity(new Intent(Login.this,P_login.class));}

}