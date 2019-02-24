package me.piggypiglet.botchecker.commands;

import me.piggypiglet.botchecker.core.enums.Roles;
import me.piggypiglet.botchecker.core.framework.commands.JDACommand;
import me.piggypiglet.botchecker.core.objects.Constants;
import me.piggypiglet.botchecker.core.utils.string.StringUtils;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class BotCheckCommand extends JDACommand {
    public BotCheckCommand() {
        super("status");
        options.setRole(Roles.ADMIN);
    }

    @Override
    protected void execute(GuildMessageReceivedEvent e, String[] args) {
        if (args.length >= 1 && StringUtils.equalsIgnoreCase(args[0], "gary", "barry")) {
            e.getChannel().sendMessage(args[0] + " " + (e.getGuild().getMemberById(args[0].equalsIgnoreCase("gary") ? Constants.GARY : Constants.BARRY).getOnlineStatus() != OnlineStatus.OFFLINE ? "is online." : "isn't online")).queue();
        } else {
            e.getChannel().sendMessage("Incorrect usage, correct usage is: `;status <gary/barry>`").queue();
        }
    }
}
