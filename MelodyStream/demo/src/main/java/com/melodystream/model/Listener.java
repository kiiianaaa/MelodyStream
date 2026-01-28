package com.melodystream.model;

import java.io.Serializable;

public class Listener extends User implements Serializable{
    
    public Listener(String username, String password) {
        super(username, password);
    }
}
