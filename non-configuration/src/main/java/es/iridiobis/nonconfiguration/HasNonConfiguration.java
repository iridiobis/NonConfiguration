package es.iridiobis.nonconfiguration;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Interface to be implemented by the classes that want to store their non-configuration instances
 * in a {@link NonConfigurationCache}.
 *
 * @param <T>   The type of the non-configuration instance
 */
public interface HasNonConfiguration<T> {
    /**
     * This method allows the {@link NonConfigurationManager} to initialize the non-configuration
     * instance when needed.
     *
     * @param savedInstanceState    Bundle passed to recover the non-configuration. If the state of
     *                              the non-configuration was stored in this bundle, it could be
     *                              recovered.
     * @return  The non-configuration instance of the class implementing the interface
     */
    @NonNull T createNonConfiguration(@Nullable final Bundle savedInstanceState);

}
