/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.app.community.injector.module;

import android.support.v7.app.AppCompatActivity;


import com.app.community.injector.scope.PerActivity;
import com.app.community.network.Repository;
import com.app.community.ui.presenter.AuthenticationPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by arvind on 13/10/17.
 */

@Module
public class AuthenticationModule {

    private AppCompatActivity mActivity;

    public AuthenticationModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }


    //to get presenter object
    @Provides
    @PerActivity
    AuthenticationPresenter provideProfilePresenter(Repository repository) {
        return new AuthenticationPresenter(repository);
    }
}
