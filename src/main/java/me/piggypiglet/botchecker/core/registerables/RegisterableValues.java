package me.piggypiglet.botchecker.core.registerables;

import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import org.reflections.Reflections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class RegisterableValues {
    @Getter private final Map<String, Object> values = new ConcurrentHashMap<>();

    public Reflections getReflections() {
        return (Reflections) values.get("reflections");
    }

    public Injector getInjector() {
        return (Injector) values.get("injector");
    }

    public JDA getJda() {
        return (JDA) values.get("jda");
    }
}
