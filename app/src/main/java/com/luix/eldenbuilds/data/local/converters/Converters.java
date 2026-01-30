package com.luix.eldenbuilds.data.local.converters;

import androidx.room.TypeConverter;
import com.luix.eldenbuilds.data.model.StartingClass;

public class Converters {

    @TypeConverter
    public static StartingClass toStartingClass(String value) {
        if (value == null) {
            return StartingClass.WRETCH;
        }
        try {
            return StartingClass.valueOf(value);
        } catch (IllegalArgumentException e) {
            return StartingClass.WRETCH;
        }
    }

    @TypeConverter
    public static String fromStartingClass(StartingClass startingClass) {
        return startingClass == null ? null : startingClass.name();
    }
}