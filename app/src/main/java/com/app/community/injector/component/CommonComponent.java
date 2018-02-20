package com.app.community.injector.component;



import com.app.community.injector.module.CommonModule;
import com.app.community.injector.scope.PerActivity;
import com.app.community.ui.WelcomeScreenActivity;
import com.app.community.ui.authentication.LoginActivity;
import com.app.community.ui.activity.UpdateProfileActivity;
import com.app.community.ui.authentication.VerifyAccountActivity;
import com.app.community.ui.dashboard.home.SearchActivity;
import com.app.community.ui.dashboard.home.fragment.MyOrderActivity;
import com.app.community.ui.fragment.DemoFragment;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = CommonModule.class)
public interface CommonComponent {
    void inject(LoginActivity activity);
    void inject(UpdateProfileActivity activity);
    void inject(DemoFragment activity);
    void inject(VerifyAccountActivity activity);
    void inject(SearchActivity activity);
    void inject(WelcomeScreenActivity activity);
    void inject(MyOrderActivity activity);



}
