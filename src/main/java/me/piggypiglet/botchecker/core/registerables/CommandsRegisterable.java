package me.piggypiglet.botchecker.core.registerables;

import com.google.inject.Inject;
import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.Registerable;
import me.piggypiglet.botchecker.core.framework.commands.JDACommand;
import me.piggypiglet.botchecker.core.handlers.chat.JDACommandHandler;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class CommandsRegisterable extends Registerable {
    @Inject private RegisterableValues values;
    @Inject private JDACommandHandler commandHandler;

    public CommandsRegisterable() {
        super(Registerables.COMMANDS);
    }

    @Override
    protected void execute() {
        values.getReflections().getSubTypesOf(JDACommand.class).stream().map(values.getInjector()::getInstance).forEach(commandHandler::add);
    }
}
