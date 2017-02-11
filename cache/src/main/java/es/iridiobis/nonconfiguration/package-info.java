/**
 * Classes and interfaces to provide fragments (or any other class that could need it) with
 * the ability to keep an instance of any object on configuration changes.
 * <P>
 * The idea is to use the activities onRetainCustomNonConfigurationInstance and
 * getLastCustomNonConfigurationInstance to retain not only their instances, but also those of
 * fragments.
 */
package es.iridiobis.nonconfiguration;