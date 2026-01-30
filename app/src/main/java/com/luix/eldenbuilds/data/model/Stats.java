package com.luix.eldenbuilds.data.model;

import androidx.room.ColumnInfo;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}