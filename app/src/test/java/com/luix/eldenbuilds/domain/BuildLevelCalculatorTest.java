package com.luix.eldenbuilds.domain;

import static org.junit.Assert.assertEquals;

import com.luix.eldenbuilds.data.model.StartingClass;
import com.luix.eldenbuilds.data.model.Stats;

import org.junit.Test;

public class BuildLevelCalculatorTest {

    @Test
    public void calculateLevel_returnsBaseLevel_whenStatsAreBaseStats() {
        StartingClass startingClass = StartingClass.SAMURAI;
        Stats stats = new Stats(
                startingClass.baseVigor,
                startingClass.baseMind,
                startingClass.baseEndurance,
                startingClass.baseStrength,
                startingClass.baseDexterity,
                startingClass.baseIntelligence,
                startingClass.baseFaith,
                startingClass.baseArcane
        );

        int level = BuildLevelCalculator.calculateLevel(startingClass, stats);

        assertEquals(startingClass.baseLevel, level);
    }

    @Test
    public void calculateLevel_increasesByOne_forEachExtraStatPoint() {
        StartingClass startingClass = StartingClass.VAGABOND;
        Stats stats = new Stats(
                startingClass.baseVigor + 10,
                startingClass.baseMind + 5,
                startingClass.baseEndurance,
                startingClass.baseStrength,
                startingClass.baseDexterity,
                startingClass.baseIntelligence,
                startingClass.baseFaith,
                startingClass.baseArcane
        );

        int level = BuildLevelCalculator.calculateLevel(startingClass, stats);

        assertEquals(startingClass.baseLevel + 15, level);
    }

    @Test
    public void calculateLevel_neverGoesBelowBaseLevel() {
        StartingClass startingClass = StartingClass.HERO;
        Stats stats = new Stats(1, 1, 1, 1, 1, 1, 1, 1);

        int level = BuildLevelCalculator.calculateLevel(startingClass, stats);

        assertEquals(startingClass.baseLevel, level);
    }
}
