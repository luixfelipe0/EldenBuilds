package com.luix.eldenbuilds.data.model;

public enum StartingClass {
    VAGABOND("Vagabond", 9, 15, 10, 11, 14, 13, 9, 9, 7),
    WARRIOR("Warrior", 8, 11, 12, 11, 10, 16, 10, 8, 9),
    HERO("Hero", 7, 14, 9, 12, 16, 9, 7, 8, 11),
    BANDIT("Bandit", 5, 10, 11, 10, 9, 13, 9, 8, 14),
    ASTROLOGER("Astrologer", 6, 9, 15, 9, 8, 12, 16, 7, 9),
    PROPHET("Prophet", 7, 10, 14, 8, 11, 10, 7, 16, 10),
    SAMURAI("Samurai", 9, 12, 11, 13, 12, 15, 9, 8, 8),
    PRISONER("Prisoner", 9, 11, 12, 11, 11, 14, 14, 6, 9),
    CONFESSOR("Confessor", 10, 10, 13, 10, 12, 12, 9, 14, 9),
    WRETCH("Wretch", 1, 10, 10, 10, 10, 10, 10, 10, 10);

    private final String displayName;
    public final int baseLevel;
    public final int baseVigor;
    public final int baseMind;
    public final int baseEndurance;
    public final int baseStrength;
    public final int baseDexterity;
    public final int baseIntelligence;
    public final int baseFaith;
    public final int baseArcane;

    StartingClass(String displayName, int baseLevel, int vig, int min, int end, int str, int dex, int intel, int fai, int arc) {
        this.displayName = displayName;
        this.baseLevel = baseLevel;
        this.baseVigor = vig;
        this.baseMind = min;
        this.baseEndurance = end;
        this.baseStrength = str;
        this.baseDexterity = dex;
        this.baseIntelligence = intel;
        this.baseFaith = fai;
        this.baseArcane = arc;
    }

    public String getDisplayName() {
        return displayName;
    }
}