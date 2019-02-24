package me.piggypiglet.botchecker;

import com.google.inject.Injector;
import me.piggypiglet.botchecker.core.framework.BinderModule;
import org.reflections.Reflections;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class BotCheckerBootstrap {
    public static void main(String[] args) throws Exception {
        Injector injector = new BinderModule(BotCheckerBootstrap.class).createInjector();
        injector.injectMembers(BotCheckerBootstrap.class.newInstance());

        new BotChecker().start(injector, new Reflections("me.piggypiglet.botchecker"));
    }
}