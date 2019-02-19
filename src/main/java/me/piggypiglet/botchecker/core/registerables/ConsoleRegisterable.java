package me.piggypiglet.botchecker.core.registerables;

import me.piggypiglet.botchecker.core.enums.Registerables;
import me.piggypiglet.botchecker.core.framework.Registerable;
import me.piggypiglet.botchecker.core.tasks.Task;

import java.util.Scanner;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ConsoleRegisterable extends Registerable {
    public ConsoleRegisterable() {
        super(Registerables.CONSOLE);
    }

    @Override
    protected void execute() {
        Task.async(r -> {
            Scanner input = new Scanner(System.in);

            while (true) {
                switch (input.nextLine().toLowerCase()) {
                    case "stop": System.exit(0); break;
                }
            }
        }, "Console Command Monitor");
    }
}
