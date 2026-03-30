package com.luix.eldenbuilds.domain.usecase;

import androidx.lifecycle.LiveData;

import com.luix.eldenbuilds.data.model.Build;
import com.luix.eldenbuilds.data.repository.BuildRepository;

import java.util.List;

public class GetUserBuildsUseCase {

    private final BuildRepository buildRepository;

    public GetUserBuildsUseCase(BuildRepository buildRepository) {
        this.buildRepository = buildRepository;
    }

    public LiveData<List<Build>> execute() {
        return buildRepository.getAllBuilds();
    }

}
