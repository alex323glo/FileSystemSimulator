package com.alex323glo.os.fss.model.descriptor;

/**
 * FileDescriptor ID implementation. Based on String type of ID value.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see DescriptorID
 */
public class StringDescriptorID implements DescriptorID<String> {

    private String id;

    /**
     * Initial constructor.
     *
     * @param id initial field value.
     */
    public StringDescriptorID(String id) {
        this.id = id;
    }

    /**
     * Gets Descriptor ID value.
     *
     * @return value of Descriptor ID.
     */
    @Override
    public String getID() {
        return id;
    }

    /**
     * Sets Descriptor ID value.
     *
     * @param id initial Descriptor ID value.
     */
    @Override
    public void setID(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringDescriptorID that = (StringDescriptorID) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                '}';
    }
}
