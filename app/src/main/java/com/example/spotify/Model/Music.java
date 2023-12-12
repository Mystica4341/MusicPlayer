package com.example.spotify.Model;

import android.net.Uri;

public class Music {
    String id, name, type, artistName;
    Uri musicURL, images;
    int duration;

    public Uri getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(Uri musicURL) {
        this.musicURL = musicURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getImages() {
        return images;
    }

    public void setImages(Uri images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Music(String id, Uri images, String name, String type, String artistName, Uri musicURL, int duration) {
        this.id = id;
        this.images = images;
        this.name = name;
        this.type = type;
        this.artistName = artistName;
        this.duration = duration;
        this.musicURL = musicURL;
    }

    public Music(){

    }
}
