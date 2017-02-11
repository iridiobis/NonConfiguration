package es.iridiobis.nonconfiguration.presentation.listeners;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;


public abstract class TextChangedListener implements TextWatcher {
    @Override
    public final void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
        //Do nothing
    }

    @Override
    public final void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
        //Do nothing
    }

    @Override
    public final void afterTextChanged(final Editable editable) {
        onTextChanged(editable.toString());
    }

    public abstract void onTextChanged(@NonNull final String newText);
}
