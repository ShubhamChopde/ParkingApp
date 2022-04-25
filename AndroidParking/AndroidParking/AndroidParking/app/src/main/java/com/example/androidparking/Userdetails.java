
package com.example.androidparking;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Userdetails extends AppCompatActivity {

    private static final String url="http://10.0.2.2/api/fetch1.php";
    ListView lv;
    String name;
    ArrayList<String>holder=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        fetchdata();
    }
    public void fetchdata(){
        lv=(ListView)findViewById(R.id.lv);
        class dbmanager extends AsyncTask<String, Void, String>{
            protected  void onPostExecute(String data){
                try{
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;
                    holder.clear();

                    for(int i =0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        String username=jo.getString("username");
                        String name=jo.getString("name");
                        String email=jo.getString("email");
                        String mobile=jo.getString("mobile");



                        holder.add(name);
                        holder.add(mobile);
                        holder.add(email);
                        holder.add(username);
                    }
                    ArrayAdapter<String> at=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,holder);
                    lv.setAdapter(at);

                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();

                }

            }
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer data = new StringBuffer();
                    String line;
                    while( (line=br.readLine())!=null){
                        data.append(line+"\n");
                    }
                    br.close();
                    return data.toString();

                }catch (Exception ex){
                    return ex.getMessage();
                }
            }
        }
        dbmanager obj = new dbmanager();
        obj.execute(url);

    }

}
