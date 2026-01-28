package com.melodystream.model;
import java.io.Serializable;

public class Artist extends User implements Serializable{
    
    private double income;

    public Artist(String username, String password) {
        super(username, password);
        this.income = 0.0;
    }

    public double getIncome() {
        return income;
    }

    public void addIncome(double amount) {
        this.income += amount;
    }
}