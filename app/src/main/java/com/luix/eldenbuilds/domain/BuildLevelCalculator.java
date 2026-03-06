package com.luix.eldenbuilds.domain;

import com.luix.eldenbuilds.data.model.StartingClass;
import com.luix.eldenbuilds.data.model.Stats;

public final class BuildLevelCalculator {

    private BuildLevelCalculator() {
    }

    public static int calculateLevel(StartingClass startingClass, Stats stats) {
        if (startingClass == null) {
            throw new IllegalArgumentException("startingClass cannot be null");
        }
        if (stats == null) {
            throw new IllegalArgumentException("stats cannot be null");
        }

        int currentStatsSum = stats.getVigor()
                + stats.getMind()
                + stats.getEndurance()
                + stats.getStrength()
                + stats.getDexterity()
                + stats.getIntelligence()
                + stats.getFaith()
                + stats.getArcane();

        int baseStatsSum = startingClass.baseVigor
                + startingClass.baseMind
                + startingClass.baseEndurance
                + startingClass.baseStrength
                + startingClass.baseDexterity
                + startingClass.baseIntelligence
                + startingClass.baseFaith
                + startingClass.baseArcane;

        int calculatedLevel = startingClass.baseLevel + (currentStatsSum - baseStatsSum);
        return Math.max(calculatedLevel, startingClass.baseLevel);
    }
}
