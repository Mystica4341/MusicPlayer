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
        JSONObject object = new JSONObject(string);
        for (int i = 0; i < object.length(); i++){
            Music m = new Music();
            if (object.has("album")){
                JSONObject subObj1 = object.getJSONObject("albums");
                if(subObj1.has("items")){
                    JSONObject subObj2 = subObj1.getJSONObject("items");
                    if (subObj2.getString("album_type").equals("single") && subObj2.getString("total_track").equals("1")){
                        m.setName(subObj2.getString("name"));
                        m.setId(subObj2.getString("id"));
                        if (subObj2.has("artists")){
                            JSONObject subObj3 = subObj2.getJSONObject("artists");
                            if (subObj3.length() > 1){
                                for (int y= 0; y < subObj3.length(); y++){
                                    String holder = null;
                                    holder += subObj3.getString("name") + " ";
                                    m.setArtistName(holder);
                                }
                            } else
                            m.setArtistName(subObj3.getString("name"));
                        }
                    }
                }
            }
            lsMusic.add(m);
        }
    }

}
