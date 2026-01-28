package com.melodystream.model;

public abstract class Audio {
    private String id;
    private String title;
    private String artist;
    private int duration; 
    private int likes;

    public Audio(String id, String title, String artist, int duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.likes = 0;
    }

    public abstract void play();

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

     public int getLikes() {
        return likes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void like() {
        this.likes++;
    }
}
