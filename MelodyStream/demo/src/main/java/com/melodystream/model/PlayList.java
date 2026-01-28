package com.melodystream.model;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class PlayList implements Serializable{
    
    private String name;
    private String ownerUsername;
    private List<String> audioIds;

    public void Playlist(String name, String ownerUsername) {
        this.name = name;
        this.ownerUsername = ownerUsername;
        this.audioIds = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public List<String> getAudioIds() {
        return audioIds;
    }
}