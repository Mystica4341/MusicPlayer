package com.example.spotify.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotify.Control.AccountControl;
import com.example.spotify.R;

public class DangNhapActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button btnDangKy, btnDangNhap;
    TextView tvQuenMK;
    EditText edtTK, edtMK;
    AccountControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addControl();
        addEvent();
        Intent intent = getIntent();
        edtTK.setText(intent.getStringExtra("taikhoan"));
        edtMK.setText(intent.getStringExtra("matkhau"));
    }
    public void addControl(){
        btnDangNhap= (Button) findViewById(R.id.btnDangNhap);
        btnDangKy=(Button)findViewById(R.id.btnChuyenDangKy);
        tvQuenMK=(TextView) findViewById(R.id.tvQuenMK);
        edtTK = (EditText)findViewById(R.id.edtTaiKhoan);
        edtMK = (EditText)findViewById(R.id.edtMatKhau);
    }
    public void addEvent(){
        accountActive();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (control.checkTaiKhoan(String.valueOf(edtTK.getText()), String.valueOf(edtMK.getText()))) {
                    Intent intentCon3 = new Intent(DangNhapActivity.this, MainActivity.class);
                    startActivity(intentCon3);
                } else{
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    edtTK.setText("");
                    edtMK.setText("");
                }
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCon1 = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intentCon1);
            }
        });
        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCon2 = new Intent(DangNhapActivity.this,QuenMKActivity.class);
                startActivity(intentCon2);
            }
        });
    }
    public void accountActive(){
        control = new AccountControl(getApplicationContext(), AccountControl.DATABASE_NAME, null, 1);
        control.onCreate(db);
    }
}