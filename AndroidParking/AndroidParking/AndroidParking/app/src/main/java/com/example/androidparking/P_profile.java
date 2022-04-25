package com.example.androidparking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class P_profile extends AppCompatActivity {
    private Button btn_add, btn_booking, btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_profile);

        btn_add = findViewById(R.id.btn_add);
        btn_booking = findViewById(R.id.btn_booking);
        btn_logout = findViewById(R.id.btn_logout);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(P_profile.this, P_add_delete.class);
                startActivity(intent);
            }
        });
        btn_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(P_profile.this, P_retreive.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(P_profile.this, P_login.class);
                startActivity(intent);
                finish();
                Toast.makeText(P_profile.this, "Successfully Logout", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
