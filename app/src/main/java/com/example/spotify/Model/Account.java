package com.example.spotify.Model;

public class Account {
    String taiKhoan, matKhau;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Account(int id, String taiKhoan, String matKhau) {
        this.id = id;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    public Account(){

    }
}
