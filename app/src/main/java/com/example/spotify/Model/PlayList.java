package com.example.spotify.Model;

import android.net.Uri;

public class PlayList {
    String id, name, artist;

    public PlayList() {
    }

    public PlayList(String id, String name, String artist, Uri imageMusic, int duration, Uri musicURL) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.imageMusic = imageMusic;
        this.duration = duration;
        this.musicURL = musicURL;
    }

    public String getId() {
        return id;
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

    public Uri getImageMusic() {
        return imageMusic;
    }

    public void setImageMusic(Uri imageMusic) {
        this.imageMusic = imageMusic;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Uri getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(Uri musicURL) {
        this.musicURL = musicURL;
    }

    int duration;
    Uri musicURL, imageMusic;
}
