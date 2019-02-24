package me.piggypiglet.botchecker.core.storage.file;

import com.google.inject.Inject;

import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Config {
    @Inject private static GFile gFile;

    public static String getString(String path) {
        return gFile.getFileConfiguration("config").getString(path);
    }

    public static List<String> getStringList(String path) {
        return gFile.getFileConfiguration("config").getStringList(path);
    }
}
