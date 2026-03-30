package com.luix.eldenbuilds.core.di;

import com.luix.eldenbuilds.data.repository.BuildRepository;

public class AppContainer {

    public final BuildRepository buildRepository;

    public AppContainer() {
        buildRepository = new BuildRepository();
    }
}
