package com.melodystream.view;

import com.melodystream.model.*;
import com.melodystream.repository.*;
import com.melodystream.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CLI {

    private final AuthService authService;
    private final PlayerService playerService;
    private final SearchService searchService;
    private final RecommendationService recommendationService;
    private final UserRepository userRepository;
    private final AudioRepository audioRepository;

    private Listener currentUser;
    private final Scanner scanner = new Scanner(System.in);

    public CLI(AuthService authService,
               PlayerService playerService,
               SearchService searchService,
               RecommendationService recommendationService,
               UserRepository userRepository,
               AudioRepository audioRepository) {

        this.authService = authService;
        this.playerService = playerService;
        this.searchService = searchService;
        this.recommendationService = recommendationService;
        this.userRepository = userRepository;
        this.audioRepository = audioRepository;
    }

    // ===== START =====
    public void start() {
        while (true) {
            System.out.println("\n1. Login:");
            System.out.println("2. Register:");
            System.out.println("0. Exit:");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    mainMenu();
                    break;
                case "2":
                    register();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // ===== AUTH =====
    private void register() {
    System.out.print("Username: ");
    String username = scanner.nextLine();

    System.out.print("Password: ");
    String password = scanner.nextLine();

    Listener user = new Listener(username, password);

    userRepository.addUser(user);

    try {
        userRepository.save();
        System.out.println("Registration successful.");
    } catch (IOException e) {
        System.out.println("Error saving user data.");
    }
}


    private void login() {
        while (true) {
            try {
                System.out.print("Username: ");
                String username = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                currentUser = (Listener) authService.login(username, password);
                System.out.println("Login successful.");
                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // ===== MAIN MENU =====
    private void mainMenu() {
        while (true) {
            System.out.println("\n1. Add song");
            System.out.println("2. Search");
            System.out.println("3. Play next");
            System.out.println("4. Play previous");
            System.out.println("5. Recommend");
            System.out.println("0. Logout");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addSong();
                    break;
                case "2":
                    searchMenu();
                    break;
                case "3":
                    playNext();
                    break;
                case "4":
                    playPrevious();
                    break;
                case "5":
                    recommend();
                    break;
                case "0":
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // ===== SONG =====
    private void addSong() {

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Artist: ");
        String artist = scanner.nextLine();

        System.out.print("Duration (seconds): ");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Lyrics: ");
        String lyrics = scanner.nextLine();

        Song song = new Song(
                String.valueOf(System.currentTimeMillis()),
                title,
                artist,
                duration,
                genre,
                lyrics
        );

        audioRepository.addAudio(song);

        try {
            audioRepository.save();
            System.out.println("Song added successfully.");
        } catch (IOException e) {
            System.out.println("Error saving song data.");
        }
    }



    // ===== SEARCH =====
    private void searchMenu() {
        System.out.print("Title keyword: ");
        String title = scanner.nextLine();

        System.out.print("Artist: ");
        String artist = scanner.nextLine();

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        List<Audio> results =
                searchService.search(title, artist, genre);

        if (results.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        for (Audio a : results) {
            System.out.println(a.getTitle());
            playerService.addToQueue(a);
        }

        System.out.println("Results added to play queue.");
    }

    // ===== PLAYER =====
    private void playNext() {
        try {
            playerService.playNext();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void playPrevious() {
        try {
            playerService.playPrevious();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // ===== RECOMMEND =====
    private void recommend() {
        List<Song> songs =
                recommendationService.recommend(currentUser);

        if (songs.isEmpty()) {
            System.out.println("No recommendations yet.");
            return;
        }

        System.out.println("Recommended for you:");
        for (Song s : songs) {
            System.out.println(s.getTitle());
        }
    }
}
