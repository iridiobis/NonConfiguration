package es.iridiobis.nonconfiguration.core;

import android.support.annotation.NonNull;

public abstract class BasePresenter<V> implements Presenter<V> {
    private V view;

    @Override
    public final void attach(final @NonNull V view) {
        if (this.view != null) {
            throw new IllegalStateException("Already attached to a view");
        }
        this.view = view;
        onViewAttached();
    }

    @Override
    public final void detach(final V view) {
        if (this.view == null) {
            throw new IllegalStateException("Detaching a presenter not attached to a view");
        } else if (this.view != view) {
            throw new IllegalStateException("This is not the attached view");
        }
        beforeViewDetached();
        this.view = null;
    }

    protected final boolean hasView() {
        return view != null;
    }

    protected final V getView() {
        return view;
    }

    protected abstract void onViewAttached();

    protected void beforeViewDetached() {
        //Empty by default, could be necessary to override on children
    }

}
