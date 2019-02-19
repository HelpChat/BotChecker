package me.piggypiglet.botchecker.core.framework.commands;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class ConsoleCommand {
    private final String[] commands;

    protected ConsoleCommand(String... commands) {
        this.commands = commands;
    }

    protected abstract void execute(String[] args);
}
