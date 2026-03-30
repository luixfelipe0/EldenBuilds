package com.luix.eldenbuilds.feature.builds.presentation.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.luix.eldenbuilds.data.repository.BuildRepository;
import com.luix.eldenbuilds.domain.usecase.GetUserBuildsUseCase;
import com.luix.eldenbuilds.ui.viewmodel.BuildViewModel;

public class BuildViewModelFactory implements ViewModelProvider.Factory {

    private final BuildRepository buildRepository;
    private final GetUserBuildsUseCase getUserBuildsUseCase;

    public BuildViewModelFactory(GetUserBuildsUseCase getUserBuildsUseCase, BuildRepository buildRepository) {
        this.getUserBuildsUseCase = getUserBuildsUseCase;
        this.buildRepository = buildRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return (T) new BuildViewModel(getUserBuildsUseCase, buildRepository);

    }


}
