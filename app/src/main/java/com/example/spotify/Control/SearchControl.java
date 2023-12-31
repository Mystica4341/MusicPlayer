package com.example.spotify.Control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotify.Model.Artist;
import com.example.spotify.Model.Music;
import com.example.spotify.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchControl {

    public ArrayList<Music> lsMusic = new ArrayList<>();


    public void connectAPI(String url, Context context) {
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
                    Music m = new Music();
                    Artist a = new Artist();
                    JSONObject subObj1 = jsonArray.getJSONObject(y);
                    m.setId(subObj1.getString("id"));
                    m.setName(subObj1.getString("title"));
                    m.setDuration(30);
                    m.setMusicURL(Uri.parse(subObj1.getString("preview")));
                    if (subObj1.has("artist")) {
                        JSONObject subObj2 = subObj1.getJSONObject("artist");
                        m.setArtistName(subObj2.getString("name"));
                        a.setId(subObj2.getString("id"));
                        a.setName(subObj2.getString("name"));
                    }
                    if (subObj1.has("album")){
                        JSONObject subObj2 = subObj1.getJSONObject("album");
                        m.setImages(Uri.parse(subObj2.getString("cover")));
                    }
                    lsMusic.add(m);
                }
            }
        }
    }
    public ArrayList<Music> getData(Context context, String url){
        connectAPI(url, context);
        return lsMusic;
    }
}
