package me.piggypiglet.botchecker.core.enums;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public enum Roles {
    EVERYBODY(1),
    HELPFUL(2),
    TRUSTED(3),
    ADMIN(4);

    private int priority;

    Roles(int priority) {
        this.priority = priority;
    }

    public static boolean isRoleOrUnder(Roles roleToCheck, Roles role) {
        return role.priority >= roleToCheck.priority;
    }
}
