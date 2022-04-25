package com.example.androidparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.TextView;

public class Booking extends AppCompatActivity {



        private TextView owner_id, user_name, name, v_model, v_no, mobile, time_slot;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_booking);

            owner_id = findViewById(R.id.owner_id);
            user_name = findViewById(R.id.user_name);
            name = findViewById(R.id.name);
            v_model = findViewById(R.id.v_model);
            v_no = findViewById(R.id.v_no);
            mobile = findViewById(R.id.mobile);
            time_slot = findViewById(R.id.time_slot);

            Intent intent = getIntent();
            String extraOwner_id = intent.getStringExtra("owner_id");
            String extraUser_name = intent.getStringExtra("user_name");
            String extraName = intent.getStringExtra("name");
            String extraV_model = intent.getStringExtra("v_model");
            String extraV_no = intent.getStringExtra("v_no");
            String extraMobile = intent.getStringExtra("mobile");
            String extraTime = intent.getStringExtra("time_slot");

            owner_id.setText("Owner ID is " +extraOwner_id);
            user_name.setText("Username is "+extraUser_name);
            name.setText("Name is " +extraName);
            v_model.setText("Vehicle Model is " +extraV_model);
            v_no.setText("Vehicle Number is " +extraV_no);
            mobile.setText("Mobile No. is " +extraMobile);
            time_slot.setText("Time in hour is " +extraTime);

        }
        public void details(View view){
            startActivity(new Intent(Booking.this,Details.class));
        }
        public void cancelslot(View view){
            startActivity(new Intent(Booking.this,cancelslot.class));
        }
    }