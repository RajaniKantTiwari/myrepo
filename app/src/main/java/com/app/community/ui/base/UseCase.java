package com.app.community.ui.base;


import io.reactivex.Observable;

/**
 *
 */
public interface UseCase<T> {
    Observable<T> execute();
}
