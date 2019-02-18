package me.piggypiglet.botchecker;

import com.google.inject.Injector;
import lombok.Getter;
import me.piggypiglet.botchecker.core.framework.BinderModule;
import org.reflections.Reflections;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class BotCheckerBootstrap {
    @Getter private static boolean isReady = false;

    public static void main(String[] args) throws Exception {
        Injector injector = new BinderModule(BotCheckerBootstrap.class).createInjector();
        injector.injectMembers(BotCheckerBootstrap.class.newInstance());
        isReady = true;

        new BotChecker().start(new Reflections("me.piggypiglet.botchecker"), injector);
    }
}