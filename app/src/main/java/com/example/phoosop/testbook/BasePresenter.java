package com.example.phoosop.testbook;

/**
 * Created by blasterav on 27.01.16.
 */
public abstract class BasePresenter <V extends BaseView> {
    private V mView;
    public final void attachView(V view){
        if (view == null) {
            throw new NullPointerException("View must not be null");
        }
        mView = view;
    }
    protected final void detachView() {
        mView = null;
    }

    protected final V getView() {
        return mView;
    }

    protected final boolean isViewAttached() {
        return mView != null;
    }

    public String getTag() {
        return this.getClass().getSimpleName();
    }
}
