package me.piggypiglet.botchecker.core.handlers.chat;

import com.google.inject.Singleton;
import me.piggypiglet.botchecker.core.enums.Events;
import me.piggypiglet.botchecker.core.enums.Roles;
import me.piggypiglet.botchecker.core.framework.GEvent;
import me.piggypiglet.botchecker.core.framework.commands.JDACommand;
import me.piggypiglet.botchecker.core.storage.file.Config;
import me.piggypiglet.botchecker.core.utils.discord.RoleUtils;
import me.piggypiglet.botchecker.core.utils.string.StringUtils;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class JDACommandHandler extends GEvent {
    private final List<JDACommand> commands = new ArrayList<>();

    public JDACommandHandler() {
        super(Events.MESSAGE_CREATE);
    }

    @Override
    protected void execute(GenericEvent event) {
        GuildMessageReceivedEvent e = (GuildMessageReceivedEvent) event;

        String message = e.getMessage().getContentRaw().toLowerCase();
        List<String> prefixes = Config.getStringList("command-prefixes");

        if (StringUtils.startsWith(message, prefixes)) {
            for (String prefix : prefixes) {
                message = message.replaceFirst(prefix, "");
            }

            for (JDACommand command : commands) {
                if (StringUtils.startsWith(message, Arrays.asList(command.getCommands()))) {
                    for (String str : command.getCommands()) {
                        message = message.replaceFirst(str, "");
                    }

                    String[] args = message.trim().split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    args = args.length == 0 ? new String[]{} : args;

                    if (Roles.isRoleOrUnder(command.getAllowedRole(), RoleUtils.getRole(e.getMember()))) {
                        command.run(e, args);

                        return;
                    }

                    e.getChannel().sendMessage("You do not have permission for that command.").queue(s -> s.delete().queueAfter(15, TimeUnit.SECONDS));
                }
            }
        }
    }

    public void add(JDACommand... commands) {
        if (commands.length >= 1) {
            this.commands.addAll(Arrays.asList(commands));
        }
    }
}
