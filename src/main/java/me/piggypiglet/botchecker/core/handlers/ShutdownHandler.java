package me.piggypiglet.botchecker.core.handlers;

import com.google.inject.Inject;
import me.piggypiglet.botchecker.core.registerables.RegisterableValues;
import me.piggypiglet.botchecker.core.tasks.Task;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ShutdownHandler extends Thread {
    @Inject private RegisterableValues values;

    @Override
    public void run() {
        values.getJda().shutdownNow();
        Task.shutdown();
    }
}
