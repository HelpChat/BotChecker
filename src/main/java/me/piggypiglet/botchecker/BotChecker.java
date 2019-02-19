package me.piggypiglet.botchecker;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.Getter;
import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.BinderModule;
import me.piggypiglet.botchecker.core.framework.Registerable;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static me.piggypiglet.botchecker.core.enums.Registerables.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class BotChecker {
    @Getter private final Map<String, Object> values = new HashMap<>();

    void start(Injector injector) {
        Map<Registerables, Registerable> registerables =
                reflections.getSubTypesOf(Registerable.class).stream().map(injector::getInstance).collect(Collectors.toMap(Registerable::getRegisterable, registerable -> registerable));

        Stream.of(
            GFILE, JDA, CONSOLE, SHUTDOWN_HOOK
        ).map(registerables::get).forEach(r -> {
            r.run();
            values.putAll(r.getValues());
        });
    }
}