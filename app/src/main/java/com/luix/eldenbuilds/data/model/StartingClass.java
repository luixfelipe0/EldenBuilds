package com.luix.eldenbuilds.data.model;
public enum StartingClass {
    VAGABOND("Vagabond"),
    WARRIOR("Warrior"),
    HERO("Hero"),
    BANDIT("Bandit"),
    ASTROLOGER("Astrologer"),
    PROPHET("Prophet"),
    SAMURAI("Samurai"),
    PRISONER("Prisoner"),
    CONFESSOR("Confessor"),
    WRETCH("Wretch");

    private final String displayName;

    StartingClass(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}