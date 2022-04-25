package com.example.androidparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class P_add_delete extends AppCompatActivity {
    private TextView textView;
    private EditText email, slots;
    private Button btn_update;

    private static final String url="http://10.0.2.2/api/p_add_delete.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delete);

        textView = findViewById(R.id.textView1);
        email = findViewById(R.id.email);
        slots = findViewById(R.id.slots);
        btn_update = findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                P_add_delete(email.getText().toString(),slots.getText().toString());
            }
        });
    }
    public void P_add_delete (String email, String slots) {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("1")) {
                    Toast.makeText(getApplicationContext(), "Successful Changed:", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), P_profile.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Enter Correct Mailid" , Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(P_add_delete.this,"Error" +error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put( "email",email);
                params.put( "slots",slots);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }
}
