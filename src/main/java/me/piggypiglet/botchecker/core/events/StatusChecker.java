package me.piggypiglet.botchecker.core.events;

import me.piggypiglet.botchecker.core.enums.Events;
import me.piggypiglet.botchecker.core.framework.GEvent;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class StatusChecker extends GEvent {
    public StatusChecker() {
        super(Events.STATUS_CHANGE);
    }

    @Override
    protected void execute(GenericEvent event) {
        UserUpdateOnlineStatusEvent e = (UserUpdateOnlineStatusEvent) event;

        if (e.getMember().getUser().isBot()) {
            System.out.println(e.getMember().getEffectiveName());
        }
    }
}
