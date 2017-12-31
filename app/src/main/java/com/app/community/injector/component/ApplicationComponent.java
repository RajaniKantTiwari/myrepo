package com.app.community.injector.component;



import com.app.community.injector.module.ApplicationModule;
import com.app.community.injector.module.NetworkModule;
import com.app.community.injector.scope.PerApplication;
import com.app.community.network.Repository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by arvind on 02/11/17.
 */
@Singleton
@PerApplication
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    Repository repository();
}
