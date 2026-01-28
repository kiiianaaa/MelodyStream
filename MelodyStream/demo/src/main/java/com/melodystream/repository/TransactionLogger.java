package com.melodystream.repository;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class TransactionLogger {
    
    private static final String FILE_NAME = "transactions.txt";

    public void log(String userId, String type, double amount) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(LocalDate.now() + "," + userId + "," + type + "," + amount + "\n");
        } catch (IOException e) {
            System.out.println("Transaction log failed");
        }
    }
}