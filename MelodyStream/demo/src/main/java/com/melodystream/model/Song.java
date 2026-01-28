package com.melodystream.model;

import java.io.Serializable;

public class Song extends Audio implements Serializable{
    private String genre;
    private String lyrics;
    private int playCount;

    public Song(String id,
                String title,
                String artist,
                int duration,
                String genre,
                String lyrics) {

        super(id, title, artist, duration);
        this.genre = genre;
        this.lyrics = lyrics;
        this.playCount = 0;
    }

    @Override
    public void play() {
        playCount++;
    }

    public String getGenre() {
        return genre;
    }

    public String getLyrics() {
        return lyrics;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
