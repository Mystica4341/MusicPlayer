package com.example.spotify.Model;

public class Music {
    String id, images, name, type, artistName, musicURL;
    int duration;

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
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

    public Music(String id, String images, String name, String type, String artistName, String musicURL, int duration) {
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
