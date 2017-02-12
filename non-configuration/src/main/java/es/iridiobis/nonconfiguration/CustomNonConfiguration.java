package es.iridiobis.nonconfiguration;

import android.util.LongSparseArray;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Non-configuration instances of a core class and all the classes depending on it. I.e., an activity
 * and its fragments.
 * The activity will have an instance of this class returned on the onRetainCustomNonConfigurationInstance
 * method.
 */
class CustomNonConfiguration {
    private LongSparseArray<Object> instances;
    private LongSparseArray<Object> removables;
    private AtomicLong nextId;

    /**
     * Constructor, uses a given seed to provide ids for the non-configurations.
     *
     * @param seed init value for the generated ids
     */
    CustomNonConfiguration(final long seed) {
        instances = new LongSparseArray<>();
        removables = new LongSparseArray<>();
        nextId = new AtomicLong(seed);
    }

    /**
     * Retrieve the non-configuration associated to the given index.
     *
     * @param index id of the non-configuration
     * @return the non-configuration with the provided id.
     */
    Object get(final long index) {
        removables.remove(index);
        return instances.get(index);
    }

    /**
     * Add a non-configuration and returns the associated index.
     *
     * @param instance Non-configuration to be saved
     * @return id given to the saved non-configuration
     */
    long put(final Object instance) {
        clear();
        final long index = nextId.getAndIncrement();
        instances.put(index, instance);
        return index;
    }

    /**
     * Mark the non-configuration associated to the provided index to be removed. During recreation,
     * the non-configuration is recovered and unmarked. If marked because it really has to be removed,
     * it will be removed as soon as this is clear (when we try to add another non-configuration).
     *
     * @param index id of the non-configuration to be marked to be removed
     */
    void remove(final long index) {
        removables.append(index, instances.get(index));
    }

    /**
     * Retrieve the current value of the seed used to generate indexes. This has to be saved and
     * restored on recreation.
     *
     * @return the seed of indexes
     */
    long getSeed() {
        return nextId.get();
    }

    /**
     * Remove the removable non-configuration instances.
     */
    private void clear() {
        for (int position = 0; position < removables.size(); position++) {
            instances.remove(removables.keyAt(position));
        }
        removables.clear();
    }

}