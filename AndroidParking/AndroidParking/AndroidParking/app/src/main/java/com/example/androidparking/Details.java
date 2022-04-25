package com.example.androidparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Details extends AppCompatActivity {
    EditText txtvalue;
    Button btnfetch;
    ListView listview;
    Button logout;
    Button map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        txtvalue = (EditText)findViewById(R.id.editText);
        btnfetch = (Button)findViewById(R.id.buttonfetch);


        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this, Login.class);
                startActivity(intent);
            }
        });

        map = (Button)findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this, MainActivity.class);
                startActivity(intent);
            }
        });







        listview = (ListView)findViewById(R.id.listView);
        btnfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details();
            }
        });
    }



    private void details() {

        String value = txtvalue.getText().toString().trim();

        if (value.equals("")) {
            Toast.makeText(this, "Please Enter Data Value", Toast.LENGTH_LONG).show();
            return;
        }

        String url = Config5.DATA_URL + txtvalue.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Details.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }



    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config5.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String user_name = jo.getString(Config5.KEY_USER_NAME);
                String v_model = jo.getString(Config5.KEY_V_MODEL);
                String v_no = jo.getString(Config5.KEY_V_NO);
                String mobile = jo.getString(Config5.KEY_MOBILE);
                String time_slot = jo.getString(Config5.KEY_TIME_SLOT);
                String Amount = jo.getString(Config5.KEY_AMOUNT);




                final HashMap<String, String> map = new HashMap<>();
                map.put(Config5.KEY_USER_NAME, "Username is "+user_name);
                map.put(Config5.KEY_V_MODEL, "Vehicle Model is "+v_model);
                map.put(Config5.KEY_V_NO, "Vehicle Number is "+v_no);
                map.put(Config5.KEY_MOBILE, "Mobile No. is "+mobile);
                map.put(Config5.KEY_TIME_SLOT, "Time in hour is "+time_slot);
                map.put(Config5.KEY_AMOUNT, "Total Amount is \u20B9"+Amount);
                list.add(map);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                Details.this, list, R.layout.activity_mylist,
                new String[]{Config5.KEY_USER_NAME, Config5.KEY_V_MODEL, Config5.KEY_V_NO, Config5.KEY_MOBILE, Config5.KEY_TIME_SLOT, Config5.KEY_AMOUNT},
                new int[]{R.id.user_name, R.id.v_model, R.id.v_no, R.id.mobile, R.id.time_slot, R.id.Amount});

        listview.setAdapter(adapter);



    }
}

