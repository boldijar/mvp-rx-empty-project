package com.boldijarpaul.polihack.dagger;

import android.app.Application;
import android.content.Context;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;

import javax.inject.Inject;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class DaggerApp extends Application {

    @Inject
    Timber.Tree mTimberTree;

    private DaggerGraph mGraph;


    @Override
    public void onCreate() {
        super.onCreate();
        setUpFacebook();
        // build the dependency graph
        mGraph = DaggerComponent.Initializer.init(this);
        mGraph.inject(this);

        Timber.plant(mTimberTree);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Lato-Regular.ttf")
                .setFontAttrId(com.boldijarpaul.polihack.R.attr.fontPath)
                .build());
    }

    private void setUpFacebook() {
        Permission[] permissions = new Permission[] {
                Permission.USER_ABOUT_ME,
                Permission.USER_FRIENDS,
        };
        SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
                .setAppId("625994234086470")
                .setNamespace("sromkuapp")
                .setPermissions(permissions)
                .build();
        SimpleFacebook.setConfiguration(configuration);
    }

    public DaggerGraph graph() {
        return mGraph;
    }

    public static DaggerApp get(Context context) {
        return (DaggerApp) context.getApplicationContext();
    }


}
