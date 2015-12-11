package com.boldijarpaul.mvprxsample.dagger;


import com.boldijarpaul.mvprxsample.activities.MainActivity;

public interface DaggerGraph {
    void inject(DaggerApp daggerApp);

    void inject(MainActivity mainActivity);
}
