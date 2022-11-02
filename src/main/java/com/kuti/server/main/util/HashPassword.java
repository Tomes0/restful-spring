package com.kuti.server.main.util;
import lombok.SneakyThrows;

import java.security.MessageDigest;


public abstract class HashPassword {
    @SneakyThrows
    public static String GenerateHashedPassword(String raw){
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(raw.getBytes());
        return new String(messageDigest.digest());
    }
}



