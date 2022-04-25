package com.example.androidparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class P_DetailActivity extends AppCompatActivity {

    TextView tvuser_name,tvname,tvv_model,tvv_no,tvmobile,tvtime_slot,tvAmount;
    int position;
    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_detail);
        btn_logout = findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(P_DetailActivity.this, P_login.class);
                startActivity(intent);
            }
        });


        tvuser_name = findViewById(R.id.user_name);
        tvname = findViewById(R.id.name);
        tvv_model = findViewById(R.id.v_model);
        tvv_no = findViewById(R.id.v_no);
        tvmobile = findViewById(R.id.mobile);
        tvtime_slot = findViewById(R.id.time_slot);
        tvAmount = findViewById(R.id.Amount);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvuser_name.setText("Username: "+P_retreive.p_viewArrayList.get(position).getUser_name());
        tvname.setText("Name: "+P_retreive.p_viewArrayList.get(position).getName());
        tvv_model.setText("Vehicle Model: "+P_retreive.p_viewArrayList.get(position).getV_model());
        tvv_no.setText("Vehicle Number: "+P_retreive.p_viewArrayList.get(position).getV_no());
        tvmobile.setText("Mobile Number: "+P_retreive.p_viewArrayList.get(position).getMobile());
        tvtime_slot.setText("Time per Hour: "+P_retreive.p_viewArrayList.get(position).getTime_slot());
        tvAmount.setText("Total Amount: "+P_retreive.p_viewArrayList.get(position).getAmount());

    }
}
