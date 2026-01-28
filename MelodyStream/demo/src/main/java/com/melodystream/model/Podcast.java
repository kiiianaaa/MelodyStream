package com.melodystream.model;
import java.io.Serializable;

public class Podcast extends Audio implements Serializable{

    private int episodeNumber;
    private String caption;
    private int lastPlayedMinute;

    public Podcast(String id,
                   String title,
                   String artist,
                   int duration,
                   int episodeNumber,
                   String caption) {

        super(id, title, artist, duration);
        this.episodeNumber = episodeNumber;
        this.caption = caption;
        this.lastPlayedMinute = 0;
    }

    @Override
    public void play() {
        lastPlayedMinute = getDuration() / 60;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public String getCaption() {
        return caption;
    }

    public int getLastPlayedMinute() {
        return lastPlayedMinute;
    }
}
