package com.melodystream.service;

import com.melodystream.exception.UserNotFoundException;
import com.melodystream.exception.WrongPasswordException;
import com.melodystream.model.User;
import com.melodystream.repository.UserRepository;

public class AuthService {
    
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password)
            throws UserNotFoundException, WrongPasswordException {

        User user = userRepository.getByUsername(username);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        if (!user.getPassword().equals(password)) {
            throw new WrongPasswordException("Wrong password");
        }

        return user;
    }
}