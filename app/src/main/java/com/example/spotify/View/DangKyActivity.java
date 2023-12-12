package com.example.spotify.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spotify.Control.AccountControl;
import com.example.spotify.R;

public class DangKyActivity extends AppCompatActivity {
    EditText edtTK, edtMK;
    Button btnBack, btnDangKy;
    AccountControl accountControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        addControl();
        addEvent();
    }
    void addControl(){
        btnBack=(Button)findViewById(R.id.btnBack);
        btnDangKy=(Button)findViewById(R.id.btnDangKy);
        edtTK = (EditText)findViewById(R.id.edtTaiKhoan_DK);
        edtMK = (EditText)findViewById(R.id.edtMatKhau_DK);
    }
    void addEvent(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBack = new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(intentBack);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(edtTK.getText()).length() >= 4 && String.valueOf(edtTK.getText()).length() <=16) {
                    accountControl= new AccountControl(getApplicationContext(), AccountControl.DATABASE_NAME, null, 1);
                    accountControl.insertData(String.valueOf(edtTK.getText()), String.valueOf(edtMK.getText()));
                    Toast.makeText(DangKyActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                            intent.putExtra("taikhoan",String.valueOf(edtTK.getText()));
                            intent.putExtra("matkhau",String.valueOf(edtMK.getText()));
                            startActivity(intent);
                        }
                    },2000);
                }else if (String.valueOf(edtMK.getText()).length() <= 6)
                    Toast.makeText(DangKyActivity.this, "Mật khẩu quá ngắn", Toast.LENGTH_SHORT).show();
                else if (String.valueOf(edtMK.getText()).length() >= 16)
                    Toast.makeText(DangKyActivity.this, "Mật khẩu quá dài", Toast.LENGTH_SHORT).show();
            }
        });
    }
}