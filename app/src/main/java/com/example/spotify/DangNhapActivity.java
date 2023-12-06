package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DangNhapActivity extends AppCompatActivity {
Button btnDangKy;
  TextView tvQuenMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        addControl();
        addEvent();
    }
    public void addControl(){
        btnDangKy=(Button)findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCon1 = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intentCon1);
            }
        });
        tvQuenMK=(TextView) findViewById(R.id.tvQuenMK);
        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCon2 = new Intent(DangNhapActivity.this,QuenMKActivity.class);
                startActivity(intentCon2);
            }
        });
    }
    public void addEvent(){

    }
}