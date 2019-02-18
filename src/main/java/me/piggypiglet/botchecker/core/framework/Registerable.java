package me.piggypiglet.botchecker.core.framework;

import lombok.Getter;
import me.piggypiglet.botchecker.core.enums.Registerables;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public abstract class Registerable {
    @Getter private final Registerables registerable;

    protected Registerable(Registerables registerable) {
        this.registerable = registerable;
    }

    protected abstract void execute();

    public void run() {
        execute();
    }
}
