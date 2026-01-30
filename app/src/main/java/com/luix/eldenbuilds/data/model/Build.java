package com.luix.eldenbuilds.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.luix.eldenbuilds.data.local.converters.Converters;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}