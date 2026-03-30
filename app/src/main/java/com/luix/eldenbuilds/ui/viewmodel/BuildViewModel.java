package com.luix.eldenbuilds.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.luix.eldenbuilds.core.result.UiState;
import com.luix.eldenbuilds.data.model.Build;
import com.luix.eldenbuilds.data.repository.BuildRepository;
import com.luix.eldenbuilds.domain.usecase.GetUserBuildsUseCase;

import java.util.List;

public class BuildViewModel extends ViewModel {

    private final BuildRepository repository;
    private final MediatorLiveData<UiState<List<Build>>> uiState = new MediatorLiveData<>();

    public BuildViewModel(GetUserBuildsUseCase getUserBuildsUseCase, BuildRepository repository) {
        this.repository = repository;
        uiState.setValue(new UiState.Loading<>());

        uiState.addSource(getUserBuildsUseCase.execute(), builds -> {
            if (builds == null || builds.isEmpty()) {
                uiState.setValue(new UiState.Empty<>());
            } else {
                uiState.setValue(new UiState.Success<>(builds));
            }
        });
    }

    public LiveData<UiState<List<Build>>> getUiState() {
        return uiState;
    }

    public void insert(Build build) { repository.insert(build); }
    public void update(Build build) { repository.update(build); }
    public void delete(Build build) { repository.delete(build); }
}