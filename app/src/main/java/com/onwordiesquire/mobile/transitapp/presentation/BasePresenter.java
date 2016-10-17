package com.onwordiesquire.mobile.transitapp.presentation;

/**
 * Convenience base class that implements the presenter interface. It also adds methods for accessing
 * the view and ensuring its attached.
 * <p>
 * Created by michelonwordi on 10/17/16.
 */

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mvpView;

    @Override
    public void attachView(T mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {

    }

    public T getMvpView() {
        return mvpView;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    /**
     * Checks if a view is attached and take the necessary action if not.
     *
     */
    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new ViewNotAttachedRuntimeException("Please attach a view to the presenter using " +
                    "Presenter.attachView(mvpView) before calling any of its methods");
        }
    }

    /**
     * Custom runtime exception to ensure the correct practice of attaching a view before calling any presenter methods
     */
    public static class ViewNotAttachedRuntimeException extends RuntimeException {
        public ViewNotAttachedRuntimeException(String message) {
            super(message);
        }
    }
}
