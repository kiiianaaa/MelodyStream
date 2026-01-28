package com.melodystream.repository;
import com.melodystream.model.User;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    
    private Map<String, User> users = new HashMap<>();
    private static final String FILE_NAME = "users.ser";

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getByUsername(String username) {
        return users.get(username);
    }

    public Map<String, User> getAll() {
        return users;
    }

    public void save() throws IOException {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(users);
        }
    }

    @SuppressWarnings("unchecked")
    public void load() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (Map<String, User>) in.readObject();
        }
    }

}
