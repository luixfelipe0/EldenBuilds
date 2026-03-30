package com.luix.eldenbuilds;

import android.app.Application;

import com.luix.eldenbuilds.core.di.AppContainer;

public class EldenBuildsApp extends Application {

    public AppContainer appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer();
    }
}
