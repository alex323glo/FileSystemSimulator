package com.alex323glo.os.fss.model.descriptor;

import com.alex323glo.os.fss.model.file.AbstractFile;

/**
 * File Descriptor model.
 *
 * @param <T> type of File Descriptor ID value.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class FileDescriptor<T> {

    private DescriptorID<T> id;
    private AbstractFile<T> abstractFile;

    /**
     * Initial constructor.
     *
     * @param id initial field value.
     * */
    public FileDescriptor(DescriptorID<T> id) {
        if (id == null) {
            throw new NullPointerException("id is null");
        }

        this.id = id;
    }

    // TODO add doc
    public DescriptorID<T> getId() {
        return id;
    }

    public AbstractFile<T> getAbstractFile() {
        return abstractFile;
    }

    public void setAbstractFile(AbstractFile<T> abstractFile) {
        this.abstractFile = abstractFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileDescriptor<?> that = (FileDescriptor<?>) o;

        if (!id.equals(that.id)) return false;
        return abstractFile != null ? abstractFile.equals(that.abstractFile) : that.abstractFile == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "FileDescriptor{" +
                "id=" + id +
                ", abstractFile=" + abstractFile +
                '}';
    }
}
