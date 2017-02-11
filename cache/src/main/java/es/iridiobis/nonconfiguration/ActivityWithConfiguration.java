package es.iridiobis.nonconfiguration;

/**
 * Interface to be implemented by activities that have a non-configuration of their own.
 * @param <T>   Type of the non-configuration of the activity.
 */
public interface ActivityWithConfiguration<T>
        extends HasNonConfiguration<T>,
        HasNonConfigurationCache {

    /**
     * Add this method to allow to access the homonym method of the activity through the interface.
     *
     * @return using the library, this will be a {@link CustomNonConfiguration} containing all
     * the non-configuration instances
     */
    Object getLastCustomNonConfigurationInstance();

}
