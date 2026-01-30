package com.luix.eldenbuilds.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.luix.eldenbuilds.data.model.Build;

import java.util.List;

@Dao
public interface BuildDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Build build);

    @Update
    void update(Build build);

    @Delete
    void delete(Build build);

    @Query("SELECT * FROM builds ORDER BY id DESC")
    LiveData<List<Build>> getAllBuilds();

    @Query("SELECT * FROM builds WHERE id = :id")
    LiveData<Build> getBuildById(int id);

    @Query("DELETE FROM builds")
    void deleteAll();
}