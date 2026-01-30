package com.luix.eldenbuilds.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.luix.eldenbuilds.data.local.converters.Converters;
import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "builds")
public class Build implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "starting_class")
    @TypeConverters(Converters.class)
    private StartingClass startingClass;

    private int level;
    @Embedded
    private Stats stats;

    // --- Equipamentos (String por enquanto, ID ref no futuro) ---
    @ColumnInfo(name = "right_hand_weapon")
    private String rightHandWeapon;

    @ColumnInfo(name = "left_hand_weapon")
    private String leftHandWeapon;

    @ColumnInfo(name = "head_armor")
    private String headArmor;

    @ColumnInfo(name = "chest_armor")
    private String chestArmor;

    @ColumnInfo(name = "hands_armor")
    private String handsArmor;

    @ColumnInfo(name = "legs_armor")
    private String legsArmor;

    private String talisman1;
    private String talisman2;
    private String talisman3;
    private String talisman4;

    private String notes;

    public Build(String name, StartingClass startingClass, int level) {
        this.name = name;
        this.startingClass = startingClass;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StartingClass getStartingClass() {
        return startingClass;
    }

    public void setStartingClass(StartingClass startingClass) {
        this.startingClass = startingClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getRightHandWeapon() {
        return rightHandWeapon;
    }

    public void setRightHandWeapon(String rightHandWeapon) {
        this.rightHandWeapon = rightHandWeapon;
    }

    public String getLeftHandWeapon() {
        return leftHandWeapon;
    }

    public void setLeftHandWeapon(String leftHandWeapon) {
        this.leftHandWeapon = leftHandWeapon;
    }

    public String getHeadArmor() {
        return headArmor;
    }

    public void setHeadArmor(String headArmor) {
        this.headArmor = headArmor;
    }

    public String getChestArmor() {
        return chestArmor;
    }

    public void setChestArmor(String chestArmor) {
        this.chestArmor = chestArmor;
    }

    public String getHandsArmor() {
        return handsArmor;
    }

    public void setHandsArmor(String handsArmor) {
        this.handsArmor = handsArmor;
    }

    public String getLegsArmor() {
        return legsArmor;
    }

    public void setLegsArmor(String legsArmor) {
        this.legsArmor = legsArmor;
    }

    public String getTalisman1() {
        return talisman1;
    }

    public void setTalisman1(String talisman1) {
        this.talisman1 = talisman1;
    }

    public String getTalisman2() {
        return talisman2;
    }

    public void setTalisman2(String talisman2) {
        this.talisman2 = talisman2;
    }

    public String getTalisman3() {
        return talisman3;
    }

    public void setTalisman3(String talisman3) {
        this.talisman3 = talisman3;
    }

    public String getTalisman4() {
        return talisman4;
    }

    public void setTalisman4(String talisman4) {
        this.talisman4 = talisman4;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Build build = (Build) o;
        return id == build.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}