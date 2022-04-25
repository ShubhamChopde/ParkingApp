package com.example.androidparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public abstract class Home extends AppCompatActivity {

    private static final String url="http://10.0.2.2/api/fetch.php";
    ListView lv;
    String name;
    ArrayList<String> holder=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fetchdata();
    }
    public void fetchdata(){
        lv=(ListView)findViewById(R.id.lv);
        class dbmanager extends AsyncTask<String, Void, String> {
            protected  void onPostExecute(String data){
                try{
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;
                    holder.clear();

                    for(int i =0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        String name=jo.getString("name");
                        String mobile=jo.getString("mobile");
                        String email=jo.getString("email");
                        String username=jo.getString("username");

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
