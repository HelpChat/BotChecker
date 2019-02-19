package me.piggypiglet.botchecker.core.registerables;

import com.google.inject.Inject;
import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.Registerable;
import me.piggypiglet.botchecker.core.handlers.ShutdownHandler;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ShutdownHookRegisterable extends Registerable {
    @Inject private ShutdownHandler shutdownHandler;

    public ShutdownHookRegisterable() {
        super(Registerables.SHUTDOWN_HOOK);
    }

    @Override
    protected void execute() {
        Runtime.getRuntime().addShutdownHook(shutdownHandler);
    }
}
