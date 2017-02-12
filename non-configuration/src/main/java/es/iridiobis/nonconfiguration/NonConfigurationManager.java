package es.iridiobis.nonconfiguration;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Non-configuration manager. It serves to ease the use of the {@link NonConfigurationCache}, by
 * keeping part of the logic (as the non-configuration id) hidden to its users.
 * <p/>
 * Use {@link CoreNonConfigurationManager} for core classes
 *
 * @param <T> The type of the non-configuration instance of the managed class
 */
public class NonConfigurationManager<T> {

    private static final String NON_CONFIGURATION_ID_KEY = "non-configuration-id";

    private final NonConfigurationCache cache;
    private final long nonConfigurationId;
    private final T nonConfigurationInstance;

    /**
     * Only available constructor for the class. The {@link NonConfigurationCache} is picked from
     * the {@link HasNonConfigurationCache}. If the class has been recreated, the non-configuration
     * is recovered; if not, it is created.
     *
     * @param cacheOwner         Owner of the cache
     * @param configurationOwner Responsible of creating the class non-configuration (if needed)
     * @param savedInstanceState Bundle where the previous state was saved; null on creation.
     */
    public NonConfigurationManager(
            @NonNull final HasNonConfigurationCache cacheOwner,
            @NonNull final HasNonConfiguration<T> configurationOwner,
            @Nullable final Bundle savedInstanceState) {

        cache = cacheOwner.getCache();
        if (savedInstanceState == null) {
            nonConfigurationInstance = configurationOwner.createNonConfiguration(null);
            nonConfigurationId = cache.setNonConfigurationInstance(nonConfigurationInstance);
        } else {
            final long id = savedInstanceState.getLong(NON_CONFIGURATION_ID_KEY);
            final T instance = cache.getNonConfigurationInstance(id);
            if (instance == null) {
                nonConfigurationInstance = configurationOwner.createNonConfiguration(savedInstanceState);
                nonConfigurationId = cache.setNonConfigurationInstance(nonConfigurationInstance);
            } else {
                nonConfigurationId = id;
                nonConfigurationInstance = instance;
            }
        }
    }

    /**
     * Saves the information required to restore the non-instance on recreation.
     *
     * @param outState Bundle where the state has to be saved
     */
    public void saveState(@NonNull final Bundle outState) {
        outState.putLong(NON_CONFIGURATION_ID_KEY, nonConfigurationId);
    }

    /**
     * Provides the non-configuraion instance of the managed class.
     *
     * @return the non-configuration instance
     */
    @NonNull
    public T getNonConfiguration() {
        return nonConfigurationInstance;
    }

    /**
     * Call this method when the managed class is being destroyed. This method is intended to be
     * used by fragment on destroy, but can be called by any class being managed. The
     * non-configuration will be marked to be removed and will be if not needed any more.
     */
    public void onDestroy() {
        cache.removeNonConfigurationInstance(nonConfigurationId);
    }

}
