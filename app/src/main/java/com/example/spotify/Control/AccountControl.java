package com.example.spotify.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotify.Model.Account;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AccountControl extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "projectandroid";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.example.spotify/database/projectandroid.db";
    public static final String TABLE_NAME = "TaiKhoan";
    public static String IDTAIKHOAN = "id";
    private static final String TAIKHOAN = "taikhoan";
    private static final String MATKHAU = "matkhau";

    public AccountControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public boolean checkTaiKhoan(String taikhoan, String matkhau){
        ArrayList<Account> lsAccount = loadData();
        for (Account a : lsAccount) {
            if (taikhoan.equals(a.getTaiKhoan()) && matkhau.equals(a.getMatKhau()))
                return true;
            else
                continue;
        }
        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + IDTAIKHOAN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + TAIKHOAN + " TEXT NOT NULL," + MATKHAU + " TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }
    public void insertData(String taiKhoan, String matKhau) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(TAIKHOAN, taiKhoan);
        value.put(MATKHAU, matKhau);
        db.insert(TABLE_NAME, null, value);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public ArrayList<Account> loadData() {
        ArrayList<Account> result = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        do {
            Account tk = new Account();
            tk.setId(cursor.getInt(0));
            tk.setTaiKhoan(cursor.getString(1));
            tk.setMatKhau(cursor.getString(2));
            result.add(tk);
        } while (cursor.moveToNext());
        return result;
    }
}
