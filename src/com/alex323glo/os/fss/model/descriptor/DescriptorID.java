package com.alex323glo.os.fss.model.descriptor;

/**
 * Interface for FileDescriptor ID.
 *
 * @param <T> type of ID value.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see FileDescriptor
 */
public interface DescriptorID<T> {

    /**
     * Gets Descriptor ID value.
     *
     * @return value of Descriptor ID.
     */
    T getID();

    /**
     * Sets Descriptor ID value.
     *
     * @param id initial Descriptor ID value.
     */
    void setID(T id);

}
