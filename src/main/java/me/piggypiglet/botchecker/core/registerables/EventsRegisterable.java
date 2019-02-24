package me.piggypiglet.botchecker.core.registerables;

import com.google.inject.Inject;
import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.GEvent;
import me.piggypiglet.botchecker.core.framework.Registerable;
import me.piggypiglet.botchecker.core.handlers.EventHandler;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class EventsRegisterable extends Registerable {
    @Inject private EventHandler eventHandler;
    @Inject private RegisterableValues values;

    public EventsRegisterable() {
        super(Registerables.EVENTS);
    }

    @Override
    protected void execute() {
        values.getReflections().getSubTypesOf(GEvent.class).stream().map(values.getInjector()::getInstance).forEach(eventHandler.getEvents()::add);
    }
}
