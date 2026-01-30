package com.luix.eldenbuilds.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.luix.eldenbuilds.data.local.AppDatabase;
import com.luix.eldenbuilds.data.local.dao.BuildDao;
import com.luix.eldenbuilds.data.model.Build;

import java.util.List;

public class BuildRepository {

    private final BuildDao mBuildDao;
    private final LiveData<List<Build>> mAllBuilds;

    public BuildRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mBuildDao = db.buildDao();
        mAllBuilds = mBuildDao.getAllBuilds();
    }

    public LiveData<List<Build>> getAllBuilds() {
        return mAllBuilds;
    }

    public void insert(Build build) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mBuildDao.insert(build);
        });
    }

    public void update(Build build) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mBuildDao.update(build);
        });
    }

    public void delete(Build build) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mBuildDao.delete(build);
        });
    }

    public void deleteAll() {
        AppDatabase.databaseWriteExecutor.execute(mBuildDao::deleteAll);
    }
}