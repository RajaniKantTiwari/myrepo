package com.app.community;

import android.support.multidex.MultiDexApplication;

import com.app.community.injector.component.ApplicationComponent;
import com.app.community.injector.component.DaggerApplicationComponent;
import com.app.community.injector.module.ApplicationModule;
import com.orhanobut.hawk.Hawk;


/**
 * Created by  on 02/11/17.
 */

public class CommonApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;
    public static boolean isDebug=false;
    @Override
    public void onCreate() {
        super.onCreate();
        setOrientation();
        Hawk.init(this).build();
        setUpInjector();
    }

    private void setUpInjector() {
        applicationComponent = DaggerApplicationComponent.builder().
                applicationModule(new ApplicationModule(this)).build();
    }

    private void setOrientation() {
        registerActivityLifecycleCallbacks(new ActivityLifeCycle());
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
