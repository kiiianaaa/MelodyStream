package com.melodystream.service;

import com.melodystream.model.Audio;
import com.melodystream.model.Song;
import com.melodystream.repository.AudioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchService {
    
    private final AudioRepository audioRepository;

    public SearchService(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    public List<Audio> search(String titleKeyword,
                              String artist,
                              String genre) {

        List<Audio> result = new ArrayList<>();

        for (Map.Entry<String, Audio> entry : audioRepository.getAll().entrySet()) {
            Audio audio = entry.getValue();

            if (!matchesTitle(audio, titleKeyword)) continue;
            if (!matchesArtist(audio, artist)) continue;
            if (!matchesGenre(audio, genre)) continue;

            result.add(audio);
        }
        return result;
    }

    private boolean matchesTitle(Audio audio, String keyword) {
        if (keyword == null || keyword.isEmpty()) return true;
        return audio.getTitle().toLowerCase().contains(keyword.toLowerCase());
    }

    private boolean matchesArtist(Audio audio, String artist) {
        if (artist == null || artist.isEmpty()) return true;
        return audio.getArtist().equalsIgnoreCase(artist);
    }

    private boolean matchesGenre(Audio audio, String genre) {
        if (genre == null || genre.isEmpty()) return true;
        if (!(audio instanceof Song)) return false;

        Song song = (Song) audio;
        return song.getGenre().equalsIgnoreCase(genre);
    }
}
