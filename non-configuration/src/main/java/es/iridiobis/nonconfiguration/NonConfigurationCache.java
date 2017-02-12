package es.iridiobis.nonconfiguration;

/**
 * While activities can save a custom non-configuration instance during recreation, fragments do
 * not have this ability. Using the {@link NonConfigurationCache}, activities can save
 * non-configuration instances for their fragments.
 */
public class NonConfigurationCache {

    private static final long DEFAULT_SEED = 1;
    CustomNonConfiguration customNonConfiguration;

    /**
     * Only available constructor for the class. If the cache has been recreated, the
     * {@link CustomNonConfiguration} is recovered; if not, it is created.
     *
     * @param customNonConfiguration non-configuration instances the cache is storing
     */
    public NonConfigurationCache(final Object customNonConfiguration) {
        if (customNonConfiguration == null) {
            this.customNonConfiguration = new CustomNonConfiguration(DEFAULT_SEED);
        } else {
            this.customNonConfiguration = (CustomNonConfiguration) customNonConfiguration;
        }
    }

    /**
     * Call this method on the homonym method of the activity. It will return the {@link CustomNonConfiguration}
     * containing all the non-instances to be restored.
     *
     * @return the {@link CustomNonConfiguration} to be restored
     */
    public Object onRetainCustomNonConfigurationInstance() {
        return customNonConfiguration;
    }

    /**
     * Retrieve the non-configuration instance for the given index (associated to a single cache user).
     *
     * @param index the index of the non-configuration
     * @param <C>   the type of the non-configuration
     * @return the non-configuration associated to the given index
     */
    public final <C> C getNonConfigurationInstance(final long index) {
        //noinspection unchecked
        return (C) customNonConfiguration.get(index);
    }

    /**
     * Add a non-configuration and returns the associated index.
     *
     * @param instance Non-configuration to be saved
     * @return id given to the saved non-configuration
     */
    public long setNonConfigurationInstance(final Object instance) {
        return customNonConfiguration.put(instance);
    }

    /**
     * Mark the non-configuration associated to the provided index to be removed. During recreation,
     * the non-configuration is recovered and unmarked. If marked because it really has to be removed,
     * it will be properly destroyed.
     * <p/>
     * If it is not clear if the non-configuration has to be destroyed or not (i.e., when destroying
     * a fragment), call the method.
     *
     * @param index id of the non-configuration to be marked to be removed
     */
    public void removeNonConfigurationInstance(final long index) {
        customNonConfiguration.remove(index);
    }

}