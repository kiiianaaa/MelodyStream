package com.melodystream.repository;
import com.melodystream.model.Audio;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AudioRepository {

    private Map<String, Audio> audioDatabase = new HashMap<>();
    private static final String FILE_NAME = "audios.ser";

    public void addAudio(Audio audio) {
        audioDatabase.put(audio.getId(), audio);
    }

    public Audio getById(String id) {
        return audioDatabase.get(id);
    }

    public Map<String, Audio> getAll() {
        return audioDatabase;
    }

    public void save() throws IOException {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(audioDatabase);
        }
    }

    @SuppressWarnings("unchecked")
    public void load() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            audioDatabase = (Map<String, Audio>) in.readObject();
        }
    }
}
