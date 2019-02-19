package me.piggypiglet.botchecker.core.framework;

import lombok.Getter;
import me.piggypiglet.botchecker.core.enums.Events;
import me.piggypiglet.botchecker.core.tasks.GRunnable;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class GEvent {
    @Getter private Events[] events;
    protected GRunnable instance;

    protected GEvent(Events... events) {
        this.events = events;
    }

    protected abstract void execute(GenericEvent event);

    public void run(GenericEvent event, GRunnable instance) {
        this.instance = instance;
        execute(event);
    }
}