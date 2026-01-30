package com.luix.eldenbuilds.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.luix.eldenbuilds.data.model.Build;
import com.luix.eldenbuilds.data.repository.BuildRepository;

import java.util.List;

public class BuildViewModel extends AndroidViewModel {

    private final BuildRepository repository;
    private final LiveData<List<Build>> allBuilds;

    public BuildViewModel(@NonNull Application application) {
        super(application);
        repository = new BuildRepository(application);
        allBuilds = repository.getAllBuilds();
    }

    public LiveData<List<Build>> getAllBuilds() {
        return allBuilds;
    }

    public void insert(Build build) {
        repository.insert(build);
    }

    public void update(Build build) {
        repository.update(build);
    }

    public void delete(Build build) {
        repository.delete(build);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}