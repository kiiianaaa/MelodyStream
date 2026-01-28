package com.melodystream.service;

import com.melodystream.model.Audio;
import com.melodystream.model.Listener;
import com.melodystream.model.Song;
import com.melodystream.repository.AudioRepository;

import java.util.*;

public class RecommendationService {
    
    private final AudioRepository audioRepository;

    // username -> (genre -> score)
    private Map<String, Map<String, Integer>> userPreferences;

    public RecommendationService(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
        this.userPreferences = new HashMap<>();
    }

    // وقتی کاربر آهنگی را لایک می‌کند
    public void likeSong(Listener listener, Song song) {

        userPreferences.putIfAbsent(listener.getUsername(), new HashMap<>());

        Map<String, Integer> preferences =
                userPreferences.get(listener.getUsername());

        String genre = song.getGenre();
        preferences.put(genre, preferences.getOrDefault(genre, 0) + 1);

        song.like();
    }

    // پیشنهاد آهنگ
    public List<Song> recommend(Listener listener) {

        List<Song> result = new ArrayList<>();

        Map<String, Integer> preferences =
                userPreferences.get(listener.getUsername());

        if (preferences == null || preferences.isEmpty()) {
            return result;
        }

        String favoriteGenre = Collections.max(
                preferences.entrySet(),
                Map.Entry.comparingByValue()
        ).getKey();

        for (Audio audio : audioRepository.getAll().values()) {
            if (audio instanceof Song) {
                Song song = (Song) audio;

                if (song.getGenre().equalsIgnoreCase(favoriteGenre)) {
                    result.add(song);
                }
            }
        }

        return result;
    }
}
