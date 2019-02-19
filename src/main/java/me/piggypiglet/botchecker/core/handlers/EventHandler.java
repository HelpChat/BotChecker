package me.piggypiglet.botchecker.core.handlers;

import com.google.inject.Singleton;
import lombok.Getter;
import me.piggypiglet.botchecker.core.enums.Events;
import me.piggypiglet.botchecker.core.framework.GEvent;
import me.piggypiglet.botchecker.core.tasks.Task;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class EventHandler implements EventListener {
    @Getter private final List<GEvent> events = new ArrayList<>();

    @Override
    public void onEvent(GenericEvent event) {
        if (Events.contains(event)) {
            events.forEach(e -> {
                if (Arrays.asList(e.getEvents()).contains(Events.fromEvent(event))) {
                    Task.async(r -> e.run(event, r));
                }
            });
        }
    }
}
