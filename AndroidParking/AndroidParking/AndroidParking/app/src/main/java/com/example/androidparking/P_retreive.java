package com.example.androidparking;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
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
import java.util.Map;

public class P_retreive extends AppCompatActivity {

    TextView textView;
    ListView listView;
    MyAdapter adapter;
    public static ArrayList<P_view> p_viewArrayList = new ArrayList<>();
    private static final String url = "http://10.0.2.2/api/p_retrieve.php";
    P_view p_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_retreive);

        textView = findViewById(R.id.textView1);
        listView = findViewById(R.id.myListView);
        adapter = new MyAdapter(this, p_viewArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Edit Data","Delete Data"};
                builder.setTitle(p_viewArrayList.get(position).getName());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),P_DetailActivity.class)
                                        .putExtra("position",position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),P_EditActivity.class)
                                        .putExtra("position",position));
                                break;
                            case 2:
                                deleteData(p_viewArrayList.get(position).getName());
                                break;
                        }

                    }
                });
                builder.create().show();

            }
        });

        retrieveData();

    }

    private void deleteData(final String name) {

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/api/p_delete.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equalsIgnoreCase("Data Deleted")){
                    Toast.makeText(P_retreive.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(P_retreive.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(P_retreive.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void retrieveData() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    if(success.equals("1")){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String user_name = object.getString("user_name");
                            String name = object.getString("name");
                            String v_model = object.getString("v_model");
                            String v_no = object.getString("v_no");
                            String mobile = object.getString("mobile");
                            String time_slot = object.getString("time_slot");
                            String Amount = object.getString("Amount");

                            p_view = new P_view(id,user_name,name,v_model,v_no,mobile,time_slot,Amount);
                            p_viewArrayList.add(p_view);
                            adapter.notifyDataSetChanged();


                        }
                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(P_retreive.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
