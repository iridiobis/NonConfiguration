package es.iridiobis.nonconfiguration;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Non-configuration manager for core classes, namely activities. If an activity has its own
 * non-configuration instance, we can use this manager to handle it and the {@link NonConfigurationCache}
 * <P/>
 * Use {@link NonConfigurationManager} for non-core classes
 *
 * @param <T>   The type of the non-configuration instance of the activity
 */
public class CoreNonConfigurationManager<T> {

    private static final String NON_CONFIGURATION_ID_KEY = "non-configuration-id";

    private final NonConfigurationCache cache;
    private final long nonConfigurationId;
    private final T nonConfigurationInstance;

    /**
     * Only available constructor for the class. The {@link NonConfigurationCache} is build here
     * using the saved information if any. If the core class has been recreated, the non-configuration
     * is recovered; if not, it is created.
     *
     * @param configurationOwner    Responsible of recovering the last {@link CustomNonConfiguration}
     *                              and creating the class non-configuration (if needed)
     * @param savedInstanceState    Bundle where the previous state was saved; null on creation.
     */
    public CoreNonConfigurationManager(
            @NonNull final ActivityWithConfiguration<T> configurationOwner,
            @Nullable final Bundle savedInstanceState) {

        cache = new NonConfigurationCache(configurationOwner.getLastCustomNonConfigurationInstance());
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
     * Saves the information required to restore the non-instance of the activity on recreation.
     *
     * @param outState  Bundle where the state has to be saved
     */
    public void saveState(@NonNull final Bundle outState) {
        outState.putLong(NON_CONFIGURATION_ID_KEY, nonConfigurationId);
    }

    /**
     * Provides the non-configuraion instance of the activity.
     *
     * @return the non-configuration instance
     */
    public @NonNull T getNonConfiguration() {
        return nonConfigurationInstance;
    }

    /**
     * Retrieves the {@link NonConfigurationCache} associated to the core class where the
     * non-configuration instances of all the objects depending on the current core class will be
     * cached.
     *
     * @return  the {@link NonConfigurationCache} associated to the core class
     */
    public @NonNull NonConfigurationCache getCache() {
        return cache;
    }

    /**
     * Call this method on the homonym method of the activity. It will return the {@link CustomNonConfiguration}
     * containing all the non-instances to be restored.
     *
     * @return the {@link CustomNonConfiguration} to be restored
     */
    public Object onRetainCustomNonConfigurationInstance() {
        return cache.onRetainCustomNonConfigurationInstance();
    }
}
