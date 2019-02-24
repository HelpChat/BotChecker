package me.piggypiglet.botchecker.commands;

import me.piggypiglet.botchecker.core.enums.Roles;
import me.piggypiglet.botchecker.core.framework.commands.JDACommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Test extends JDACommand {
    public Test() {
        super("test");
        options.setRole(Roles.ADMIN).setDescription("Admin only!!!!!1!11!");
    }

    @Override
    protected void execute(GuildMessageReceivedEvent e, String[] args) {
        e.getChannel().sendMessage("test").queue();
    }
}
