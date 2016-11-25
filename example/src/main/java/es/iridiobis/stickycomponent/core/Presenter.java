package es.iridiobis.stickycomponent.core;

import android.support.annotation.NonNull;

public interface Presenter<V> {
    void attach(@NonNull V view);

    void detach(V view);
}
