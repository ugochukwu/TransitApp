package com.onwordiesquire.mobile.transitapp.presentation;

/**
 * Created by michelonwordi on 10/17/16.
 *
 * This interface represents base methods that must be implemented by any class wishing to act as a
 * presenter from the Model View Presenter pattern.
 */

public interface Presenter<T extends MvpView> {

    void attachView(T mvpView);

    void detachView();
}
