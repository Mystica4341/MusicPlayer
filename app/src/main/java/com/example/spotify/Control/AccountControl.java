package com.example.spotify.Control;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotify.Model.Account;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class AccountControl {
    ArrayList<Account> lsAccount = new ArrayList<>();

    public void connectAPI(Context context) {
        String url = "http://192.168.1.2/taikhoan.json";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    parseData(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }

    @SuppressLint("SuspiciousIndentation")
    public void parseData(String string) throws JSONException {
        JSONObject object = new JSONObject(string);
        for (int i = 0; i < object.length(); i++) {
            if (object.has("data")) {
                JSONArray jsonArray = object.getJSONArray("data");
                for (int y = 0; y < jsonArray.length(); y++) {
                    Account a = new Account();
                    JSONObject subObj1 = jsonArray.getJSONObject(y);
                    a.setId(subObj1.getString("id"));
                    a.setTaiKhoan(subObj1.getString("taikhoan"));
                    a.setMatKhau(subObj1.getString("matkhau"));
                    lsAccount.add(a);
                }
            }
        }
    }
    public boolean checkTaiKhoan(String taikhoan, String matkhau){
        for(Account a: lsAccount){
            if(taikhoan.equals(a.getTaiKhoan()) && matkhau.equals(a.getMatKhau()))
                return true;
            else
                return false;
        }
        return false;
    }
}
