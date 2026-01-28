package com.melodystream.model;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class User implements Serializable{
    
    private String username;
    private String password;
    private double balance;
    private LocalDate signupDate;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.signupDate = LocalDate.now();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDate getSignupDate() {
        return signupDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }
}
