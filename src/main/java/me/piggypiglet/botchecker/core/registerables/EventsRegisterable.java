package me.piggypiglet.botchecker.core.registerables;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.BinderModule;
import me.piggypiglet.botchecker.core.framework.GEvent;
import me.piggypiglet.botchecker.core.framework.Registerable;
import me.piggypiglet.botchecker.core.handlers.EventHandler;
import org.reflections.Reflections;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class EventsRegisterable extends Registerable {
    @Inject private @BinderModule.Reflections Reflections reflections;
    @Inject private @BinderModule.Guice Injector guice;
    @Inject private EventHandler eventHandler;

    public EventsRegisterable() {
        super(Registerables.EVENTS);
    }

    @Override
    protected void execute() {
        reflections.getSubTypesOf(GEvent.class).stream().map(guice::getInstance).forEach(eventHandler.getEvents()::add);
    }
}
