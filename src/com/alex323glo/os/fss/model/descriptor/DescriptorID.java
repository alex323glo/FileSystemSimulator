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

    // TODO add doc
    T getID();
    void setID(T id);
    int hashCode();
    // TODO fix logic

}
