package com.luix.eldenbuilds.data.model;

import androidx.room.ColumnInfo;

import java.io.Serializable;

public class Stats implements Serializable {

    @ColumnInfo(name = "vigor")
    private int vigor;

    @ColumnInfo(name = "mind")
    private int mind;

    @ColumnInfo(name = "endurance")
    private int endurance;

    @ColumnInfo(name = "strength")
    private int strength;

    @ColumnInfo(name = "dexterity")
    private int dexterity;

    @ColumnInfo(name = "intelligence")
    private int intelligence;

    @ColumnInfo(name = "faith")
    private int faith;

    @ColumnInfo(name = "arcane")
    private int arcane;

    public Stats(int vigor, int mind, int endurance, int strength, int dexterity, int intelligence, int faith, int arcane) {
        this.vigor = vigor;
        this.mind = mind;
        this.endurance = endurance;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.faith = faith;
        this.arcane = arcane;
    }

    public int getVigor() {
        return vigor;
    }

    public void setVigor(int vigor) {
        this.vigor = vigor;
    }

    public int getMind() {
        return mind;
    }

    public void setMind(int mind) {
        this.mind = mind;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getFaith() {
        return faith;
    }

    public void setFaith(int faith) {
        this.faith = faith;
    }

    public int getArcane() {
        return arcane;
    }

    public void setArcane(int arcane) {
        this.arcane = arcane;
    }


}