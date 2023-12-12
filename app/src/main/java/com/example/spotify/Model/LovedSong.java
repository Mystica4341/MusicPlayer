package com.example.spotify.Model;

import android.net.Uri;

public class LovedSong {
    String id, name, artist;
    int duration;

    public LovedSong(String id, String name, String artist, int duration, Uri musicURL, Uri image) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.musicURL = musicURL;
        this.image = image;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public LovedSong() {
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

    public Uri getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(Uri musicURL) {
        this.musicURL = musicURL;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }


    Uri musicURL, image;
}
