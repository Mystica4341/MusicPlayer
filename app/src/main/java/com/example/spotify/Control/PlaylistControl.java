package com.example.spotify.Control;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.spotify.Model.Artist;
import com.example.spotify.Model.Music;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class PlaylistControl {

    public ArrayList<Music> lsMusic = new ArrayList<>();

    @SuppressLint("SuspiciousIndentation")
    public ArrayList<Music> parseData(String url, String keyword) throws JSONException {
        JSONObject Jobject = new JSONObject(url);
        JSONArray jsonArray = Jobject.getJSONArray(keyword);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            Music m = new Music();
            m.setId(object.getString("id"));
            m.setName(object.getString("name"));
            m.setArtistName(object.getString("artist"));
            m.setDuration(object.getInt("duration"));
            m.setMusicURL(object.getString("musicURL"));
            lsMusic.add(m);
        }
        return lsMusic;
    }

}
