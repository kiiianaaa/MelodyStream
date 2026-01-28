package com.melodystream;

import com.melodystream.repository.*;
import com.melodystream.service.*;
import com.melodystream.view.CLI;

public class Main {

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        AudioRepository audioRepository = new AudioRepository();

        AuthService authService = new AuthService(userRepository);
        PlayerService playerService = new PlayerService();
        SearchService searchService = new SearchService(audioRepository);
        RecommendationService recommendationService =
                new RecommendationService(audioRepository);

        CLI cli = new CLI(
                authService,
                playerService,
                searchService,
                recommendationService,
                userRepository,
                audioRepository
        );

        cli.start();
    }
}
