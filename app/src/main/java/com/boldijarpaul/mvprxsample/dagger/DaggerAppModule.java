package com.boldijarpaul.mvprxsample.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaggerAppModule {

    private final DaggerApp mApp;

    public DaggerAppModule(DaggerApp app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApp;
    }
}

