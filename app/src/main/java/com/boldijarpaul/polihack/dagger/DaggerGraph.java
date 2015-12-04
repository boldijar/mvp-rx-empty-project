package com.boldijarpaul.polihack.dagger;


import com.boldijarpaul.polihack.MainActivity;

public interface DaggerGraph {
    void inject(DaggerApp daggerApp);

    void inject(MainActivity mainActivity);
}
