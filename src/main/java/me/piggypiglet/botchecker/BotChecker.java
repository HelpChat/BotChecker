package me.piggypiglet.botchecker;

import com.google.inject.Injector;
import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.Registerable;
import me.piggypiglet.botchecker.core.registerables.RegisterableValues;
import org.reflections.Reflections;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static me.piggypiglet.botchecker.core.enums.Registerables.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class BotChecker {
    void start(Injector injector, Reflections reflections) {
        RegisterableValues values = injector.getInstance(RegisterableValues.class);
        Map<Registerables, Registerable> registerables =
                reflections.getSubTypesOf(Registerable.class).stream().map(injector::getInstance).collect(Collectors.toMap(Registerable::getRegisterable, registerable -> registerable));
        values.getValues().put("reflections", reflections);
        values.getValues().put("injector", injector);

        Registerables[] startup = {GFILE, JDA, CONSOLE, EVENTS, COMMANDS, SHUTDOWN_HOOK};

        for (Registerable registerable : Stream.of(startup).map(registerables::get).collect(Collectors.toList())) {
            registerable.run();
            values.getValues().putAll(registerable.getValues());
        }
    }
}