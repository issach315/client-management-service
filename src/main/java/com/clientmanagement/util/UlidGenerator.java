package com.clientmanagement.util;

import com.github.f4b6a3.ulid.UlidCreator;

public final class UlidGenerator {

    private UlidGenerator() {
        // Prevent instantiation
    }

    public static String generate() {
        return UlidCreator.getUlid().toString();
    }
}