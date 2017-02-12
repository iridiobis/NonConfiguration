package es.iridiobis.nonconfiguration;


/**
 * Provides {@link NonConfigurationCache}.
 * Make activities implement this interface when needed. They can use onRetainCustomNonConfigurationInstance
 * to "persist" the {@link NonConfigurationCache} and then recover it on recreation using
 * getLastCustomNonConfigurationInstance
 */
public interface HasNonConfigurationCache {
    NonConfigurationCache getCache();
}
