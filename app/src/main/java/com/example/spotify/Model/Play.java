package com.example.spotify.Model;

import android.net.Uri;

public class Play {
    String id, name, artist;
    Uri musicURL;

    public Uri getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(Uri musicURL) {
        this.musicURL = musicURL;
    }

    public Play() {
    }

    public String getId() {
        return id;
    }

    public Play(String id, String name, String artist,Uri musicURL, int duration) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.musicURL = musicURL;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    int duration;
}
