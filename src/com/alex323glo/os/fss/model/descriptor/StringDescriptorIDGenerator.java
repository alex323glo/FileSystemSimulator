package com.alex323glo.os.fss.model.descriptor;

import java.util.Set;
import java.util.UUID;

/**
 * FileDescriptor ID generator implementation. Based on String type of ID value.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see DescriptorIDGenerator
 */
public class StringDescriptorIDGenerator implements DescriptorIDGenerator<String> {
    /**
     * Generates new FileDescriptor ID, which is unique for KeySet (param).
     *
     * @param descriptorIDSet KeySet, which is used to check uniqueness of generated ID.
     * @return generated FileDescriptor ID.
     * @see DescriptorID
     * @see Set
     */
    @Override
    public DescriptorID<String> generate(Set<DescriptorID<String>> descriptorIDSet) {
        if (descriptorIDSet == null) {
            throw new NullPointerException("descriptorIDSet is null");
        }

        DescriptorID<String> descriptorID = new StringDescriptorID(UUID.randomUUID().toString());

        while (descriptorIDSet.contains(descriptorID)) {
            descriptorID.setID(UUID.randomUUID().toString());
        }

        return descriptorID;
    }

    /**
     * Generates new FileDescriptor ID of its String variant.
     *
     * @param stringDescriptorId String variant of generated ID.
     * @return generated FileDescriptor ID.
     * @see DescriptorID
     */
    @Override
    public DescriptorID<String> generateOfString(String stringDescriptorId) {
        if (stringDescriptorId == null) {
            throw new NullPointerException("stringDescriptorId is null");
        }

        return new StringDescriptorID(stringDescriptorId);
    }
}
