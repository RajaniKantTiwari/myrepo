package com.app.community.injector.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by arvind on 02/11/17.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
