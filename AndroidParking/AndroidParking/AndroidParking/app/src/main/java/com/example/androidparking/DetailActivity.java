package com.example.androidparking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class DetailActivity extends AppCompatActivity /*{

    TextView markertxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        markertxt=findViewById(R.id.marker);

        String title=getIntent().getStringExtra("title");
        markertxt.setText(title);
    }
}
*/
{

    private EditText owner_id;
    private EditText user_name;
    private EditText name;
    private EditText v_model;
    private EditText v_no;
    private EditText mobile;
    private EditText time_slot;

    private Button btn_submit;
    AwesomeValidation awesomeValidation;

    private static final String url="http://10.0.2.2/api/tuf.php";

    TextView markertxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        markertxt=findViewById(R.id.marker);

        String title=getIntent().getStringExtra("title");
        markertxt.setText(title);

        owner_id = findViewById(R.id.owner_id);
        user_name = findViewById(R.id.user_name);
        name = findViewById(R.id.name1);
        v_model = findViewById(R.id.v_model);
        v_no = findViewById(R.id.v_no);
        mobile = findViewById(R.id.mobile1);
        time_slot = findViewById(R.id.time_slot);
        btn_submit = findViewById(R.id.btn_submit);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.owner_id, RegexTemplate.NOT_EMPTY,R.string.invalid_owner_id);
        awesomeValidation.addValidation(this,R.id.user_name, RegexTemplate.NOT_EMPTY,R.string.invalid_user_name);
        awesomeValidation.addValidation(this,R.id.name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.v_model, RegexTemplate.NOT_EMPTY,R.string.invalid_v_model);
        awesomeValidation.addValidation(this,R.id.v_no, RegexTemplate.NOT_EMPTY,R.string.invalid_v_no);
        awesomeValidation.addValidation(this,R.id.mobile, "[5-9]{1}[0-9]{9}$",R.string.invaid_mobile);
        awesomeValidation.addValidation(this,R.id.time_slot,RegexTemplate.NOT_EMPTY,R.string.invalid_time_slot);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(), "Booking Successful", Toast.LENGTH_SHORT).show();
                    book(owner_id.getText().toString(),user_name.getText().toString(),name.getText().toString(),v_model.getText().toString(),v_no.getText().toString(),mobile.getText().toString(),time_slot.getText().toString());

                    startActivity(new Intent(DetailActivity.this,Booking.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Booking Failed", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(DetailActivity.this,DetailActivity.class));
                }
                //book(owner_id.getText().toString(),user_name.getText().toString(),name.getText().toString(),v_model.getText().toString(),v_no.getText().toString(),mobile.getText().toString(),time_slot.getText().toString());
            }
        });
    }

    public void book(final String owner_id, final String user_name,final String name, final String v_model, final String v_no, final String mobile,final String time_slot)
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(DetailActivity.this,Booking.class);
                intent.putExtra("owner_id",owner_id);
                intent.putExtra("user_name",user_name);
                intent.putExtra("name",name);
                intent.putExtra("v_model",v_model);
                intent.putExtra("v_no",v_no);
                intent.putExtra("mobile",mobile);
                intent.putExtra("time_slot",time_slot);
                startActivity(intent);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, "Error! " +error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put( "owner_id",owner_id);
                params.put( "user_name",user_name);
                params.put( "name",name);
                params.put( "v_model",v_model);
                params.put( "v_no",v_no);
                params.put( "mobile",mobile);
                params.put( "time_slot",time_slot);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void booking(View view){
        startActivity(new Intent(DetailActivity.this,Booking.class));
    }

}
