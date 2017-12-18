package com.alex323glo.os.fss.model.descriptor;

import java.util.Set;

/**
 * FileDescriptor ID generator interface.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see FileDescriptor
 * @see DescriptorID
 */
public interface DescriptorIDGenerator<T> {

    /**
     * Generates new FileDescriptor ID, which is unique for KeySet (param).
     *
     * @param descriptorIDSet KeySet, which is used to check uniqueness of generated ID.
     * @return generated FileDescriptor ID.
     *
     * @see DescriptorID
     * @see Set
     */
    DescriptorID<T> generate(Set<DescriptorID<T>> descriptorIDSet);

    /**
     * Generates new FileDescriptor ID of its String variant.
     *
     * @param stringDescriptorId String variant of generated ID.
     * @return generated FileDescriptor ID.
     *
     * @see DescriptorID
     */
    DescriptorID<T> generateOfString(String stringDescriptorId);

}
