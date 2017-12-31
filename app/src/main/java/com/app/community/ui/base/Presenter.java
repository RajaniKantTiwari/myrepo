package com.app.community.ui.base;


/**
 *
 */
public interface Presenter<T extends MvpView> {
    void attachView(T view);
}
