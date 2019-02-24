package me.piggypiglet.botchecker.core.handlers.status;

import me.piggypiglet.botchecker.core.enums.Events;
import me.piggypiglet.botchecker.core.framework.GEvent;
import me.piggypiglet.botchecker.core.objects.Constants;
import me.piggypiglet.botchecker.core.utils.string.StringUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class BotStatusHandler extends GEvent {
    public BotStatusHandler() {
        super(Events.STATUS_CHANGE);
    }

    @Override
    protected void execute(GenericEvent event) {
        UserUpdateOnlineStatusEvent e = (UserUpdateOnlineStatusEvent) event;

        if (StringUtils.equalsIgnoreCase(e.getMember().getId(), Constants.IDS) && e.getNewOnlineStatus() == OnlineStatus.OFFLINE) {
            e.getGuild().getTextChannelById(Constants.STAFF_CHAT).sendMessage(new EmbedBuilder()
                    .setTitle("\uD83D\uDD34 " + e.getMember().getEffectiveName())
                    .setDescription("just went offline, I will attempt to turn him back on.")
                    .build()).queue();
        }
    }
}
