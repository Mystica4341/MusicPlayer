package com.example.spotify.Model;

public class Account {
    String id, TaiKhoan, MatKhau;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public Account(String id, String taiKhoan, String matKhau) {
        this.id = id;
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
    }

    public Account(){

    }
}
