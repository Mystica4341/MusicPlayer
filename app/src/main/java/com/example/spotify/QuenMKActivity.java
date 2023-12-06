package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuenMKActivity extends AppCompatActivity {
    Button btnBack1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mkactivity);
        addControl();
    }
    public void addControl(){
        btnBack1=(Button)findViewById(R.id.btnBack1);
        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack1 = new Intent(QuenMKActivity.this,DangNhapActivity.class);
                startActivity(intentBack1);
            }
        });
    }
}