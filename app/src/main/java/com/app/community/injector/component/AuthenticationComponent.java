package com.app.community.injector.component;



import com.app.community.injector.module.AuthenticationModule;
import com.app.community.injector.scope.PerActivity;
import com.app.community.ui.authentication.LoginActivity;
import com.app.community.ui.activity.UpdateProfileActivity;
import com.app.community.ui.authentication.VerifyAccountActivity;
import com.app.community.ui.fragment.DemoFragment;

import dagger.Component;

/**
 * Created by arvind on 13/11/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = AuthenticationModule.class)
public interface AuthenticationComponent {
    void inject(LoginActivity activity);
    void inject(UpdateProfileActivity activity);
    void inject(DemoFragment activity);
    void inject(VerifyAccountActivity activity);


}
