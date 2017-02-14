package es.iridiobis.nonconfiguration.core;

import android.support.annotation.NonNull;

public interface Presenter<V> {
    void attach(@NonNull V view);

    void detach(V view);
}
