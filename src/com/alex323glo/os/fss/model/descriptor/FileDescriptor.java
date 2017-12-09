package com.alex323glo.os.fss.model.descriptor;

import com.alex323glo.os.fss.model.file.File;

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
    private File file;

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

    // TODO add dox
    public DescriptorID<T> getId() {
        return id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileDescriptor<?> that = (FileDescriptor<?>) o;

        if (!id.equals(that.id)) return false;
        return file != null ? file.equals(that.file) : that.file == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // TODO fix logic
}
