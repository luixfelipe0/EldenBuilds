package com.luix.eldenbuilds.data.model;

import java.io.Serializable;
import java.util.Objects;

public class Stats implements Serializable {

    private int vigor;
    private int mind;
    private int endurance;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int faith;
    private int arcane;

    public Stats() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats stats = (Stats) o;
        return vigor == stats.vigor &&
                mind == stats.mind &&
                endurance == stats.endurance &&
                strength == stats.strength &&
                dexterity == stats.dexterity &&
                intelligence == stats.intelligence &&
                faith == stats.faith &&
                arcane == stats.arcane;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vigor, mind, endurance, strength, dexterity, intelligence, faith, arcane);
    }
}
