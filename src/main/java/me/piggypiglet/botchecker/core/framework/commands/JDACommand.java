package me.piggypiglet.botchecker.core.framework.commands;

import lombok.Getter;
import me.piggypiglet.botchecker.core.enums.Roles;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class JDACommand {
    @Getter private final String[] commands;
    @Getter private Roles allowedRole = Roles.EVERYBODY;

    protected final Options options = new Options();

    protected JDACommand(String... commands) {
        this.commands = commands;
    }

    protected abstract void execute(GuildMessageReceivedEvent e, String[] args);

    public void run(GuildMessageReceivedEvent e, String[] args) {
        execute(e, args);
    }

    protected class Options {
        private Options() {}

        public Options setRole(Roles allowedRole) {
            JDACommand.this.allowedRole = allowedRole;
            return this;
        }
    }
}
