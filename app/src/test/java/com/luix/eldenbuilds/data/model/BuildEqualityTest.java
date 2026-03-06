package com.luix.eldenbuilds.data.model;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class BuildEqualityTest {

    @Test
    public void equals_detectsChangedContent_withSameId() {
        Build baseBuild = new Build("Dex Build", StartingClass.SAMURAI, 150);
        baseBuild.setId("build-1");
        baseBuild.setStats(new Stats(40, 20, 25, 18, 50, 9, 15, 10));
        baseBuild.setRightHandWeapon("Moonveil");

        Build editedBuild = new Build("Dex Build", StartingClass.SAMURAI, 150);
        editedBuild.setId("build-1");
        editedBuild.setStats(new Stats(40, 20, 25, 18, 55, 9, 15, 10));
        editedBuild.setRightHandWeapon("Moonveil");

        assertNotEquals(baseBuild, editedBuild);
    }
}
