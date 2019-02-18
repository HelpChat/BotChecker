package me.piggypiglet.botchecker.core.registerables;

import com.google.inject.Inject;
import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.Registerable;
import me.piggypiglet.botchecker.core.storage.file.GFile;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class JDARegisterable extends Registerable {
    @Inject private GFile gFile;

    public JDARegisterable() {
        super(Registerables.JDA);
    }

    @Override
    protected void execute() {
        try {
            new JDABuilder(AccountType.CLIENT)
                    .setToken(gFile.getFileConfiguration("config").getString("token"))
                    .setStatus(OnlineStatus.ONLINE)
                    .build();
        } catch (Exception ignored) {}
    }
}
