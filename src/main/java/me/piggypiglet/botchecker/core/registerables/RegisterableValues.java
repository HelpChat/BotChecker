package me.piggypiglet.botchecker.core.registerables;

import com.google.inject.Inject;
import me.piggypiglet.botchecker.BotChecker;
import net.dv8tion.jda.api.JDA;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RegisterableValues {
    @Inject private BotChecker main;

    public JDA getJda() {
        return (JDA) main.getValues().get("jda");
    }
}
