package com.boldijarpaul.polihack.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module(includes = {DataModule.class})
public class DebugDataModule {

    @Provides
    @Singleton
    Timber.Tree provideTimberTree() {
        return new Timber.DebugTree();
    }
}
