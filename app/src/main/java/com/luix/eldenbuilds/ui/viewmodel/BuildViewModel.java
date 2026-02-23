package com.luix.eldenbuilds.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.luix.eldenbuilds.data.model.Build;
import com.luix.eldenbuilds.data.repository.BuildRepository;

import java.util.List;

public class BuildViewModel extends ViewModel {

    private final BuildRepository repository;
    private final LiveData<List<Build>> allBuilds;

    public BuildViewModel() {
        repository = new BuildRepository();
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
}