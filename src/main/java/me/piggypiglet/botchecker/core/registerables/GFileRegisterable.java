package me.piggypiglet.botchecker.core.registerables;

import com.google.inject.Inject;
import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.Registerable;
import me.piggypiglet.botchecker.core.storage.file.GFile;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class GFileRegisterable extends Registerable {
    @Inject private GFile gFile;

    public GFileRegisterable() {
        super(Registerables.GFILE);
    }

    @Override
    protected void execute() {
        gFile.clearMap();
        gFile.make("config", "./config.json", "/config.json");
    }
}
