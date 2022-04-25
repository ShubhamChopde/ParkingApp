package com.example.androidparking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class P_EditActivity extends AppCompatActivity {

    TextView textView;
    EditText user_name, name, v_model, v_no, mobile;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_edit);

        textView = findViewById(R.id.textView1);
        user_name = findViewById(R.id.user_name);
        name = findViewById(R.id.name);
        v_model = findViewById(R.id.v_model);
        v_no = findViewById(R.id.v_no);
        mobile = findViewById(R.id.mobile);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        user_name.setText(P_retreive.p_viewArrayList.get(position).getUser_name());
        name.setText(P_retreive.p_viewArrayList.get(position).getName());
        v_model.setText(P_retreive.p_viewArrayList.get(position).getV_model());
        v_no.setText(P_retreive.p_viewArrayList.get(position).getV_no());
        mobile.setText(P_retreive.p_viewArrayList.get(position).getMobile());

    }
    public void btn_updateData(View view) {

        final String User_name = user_name.getText().toString();
        final String Name = name.getText().toString();
        final String V_model = v_model.getText().toString();
        final String V_no = v_no.getText().toString();
        final String Mobile = mobile.getText().toString();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/api/p_update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(P_EditActivity.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(P_EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("user_name",User_name);
                params.put("name",Name);
                params.put("v_model",V_model);
                params.put("v_no",V_no);
                params.put("mobile",Mobile);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(P_EditActivity.this);
        requestQueue.add(request);

    }
}
