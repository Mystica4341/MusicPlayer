package com.example.spotify.Control;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.spotify.Model.Music;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchControl {

    ArrayList<Music> lsMusic = new ArrayList<>();

    public ArrayList<Music> connectAPI(String url, Context context){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    parseData(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                Toast.makeText(context, "Connect", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
        return lsMusic;
    }

    @SuppressLint("SuspiciousIndentation")
    public void parseData(String string) throws JSONException {
//        JSONArray jsMusic = new JSONArray(string);
        JSONObject object = new JSONObject(string);
        for (int i = 0; i < object.length(); i++){
//            JSONObject object = jsMusic.getJSONObject(i);
            Music m = new Music();
            if (object.has("data")){
                JSONArray jsonArray = object.getJSONArray("data");
                for (int y = 0; y< jsonArray.length(); y ++){
                    JSONObject subObj1 = jsonArray.getJSONObject(y);
                    m.setId(subObj1.getString("id"));
                    m.setName(subObj1.getString("title"));
                    m.setDuration(subObj1.getString("duration"));
                    if(subObj1.has("artist")){
                        JSONObject subObj2 = subObj1.getJSONObject("artist");
                        m.setArtistName(subObj2.getString("name"));
                    }
                    m.setType(subObj1.getString("type"));
                }
            }
            lsMusic.add(m);
        }
    }

}
