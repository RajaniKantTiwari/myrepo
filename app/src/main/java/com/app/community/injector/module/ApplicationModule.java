package com.app.community.injector.module;



import com.app.community.CommonApplication;
import com.app.community.injector.scope.PerApplication;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private CommonApplication application;

    public ApplicationModule(CommonApplication application) {
        this.application = application;
    }
    @Provides
    @PerApplication
    public CommonApplication provideApplication() {
        return application;
    }
}
