package com.app.community.injector.component;



import com.app.community.injector.module.DashboardModule;
import com.app.community.injector.scope.PerActivity;
import com.app.community.ui.dashboard.DashBoardActivity;
import com.app.community.ui.dashboard.home.ProductDetailsActivity;
import com.app.community.ui.dashboard.home.WelcomeHomeActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = DashboardModule.class)
public interface DashboardComponent {

    void inject(DashBoardActivity dashBoardActivity);
    void inject(ProductDetailsActivity dashBoardActivity);
    void inject(WelcomeHomeActivity welcomeActivity);

}
