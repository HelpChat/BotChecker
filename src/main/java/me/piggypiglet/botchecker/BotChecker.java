package me.piggypiglet.botchecker;

import com.google.inject.Injector;
import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.Registerable;
import org.reflections.Reflections;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static me.piggypiglet.botchecker.core.enums.Registerables.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class BotChecker {
    void start(Reflections reflections, Injector injector) {
        Map<Registerables, Registerable> registerables =
                reflections.getSubTypesOf(Registerable.class).stream().map(injector::getInstance).collect(Collectors.toMap(Registerable::getRegisterable, registerable -> registerable));

        Stream.of(
            GFILE, JDA, CONSOLE
        ).map(registerables::get).forEach(Registerable::run);
    }
}