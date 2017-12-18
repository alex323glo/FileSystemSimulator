package com.alex323glo.os.fss.model.descriptor;

/**
 * Opened File Descriptor model. Aggregates reference to FileDescriptor.
 *
 * @param <I> type of Opened File Descriptor ID value.
 * @param <T> type of aggregated File Descriptor ID value.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see FileDescriptor
 */
public class OpenedFileDescriptor<I, T> {

    private DescriptorID<I> id;
    private FileDescriptor<T> fileDescriptor;

    // TODO add doc
    public OpenedFileDescriptor(DescriptorID<I> id, FileDescriptor<T> fileDescriptor) {
        if (id == null || fileDescriptor == null) {
            throw new NullPointerException("id or fileDescriptor is null");
        }

        this.id = id;
        this.fileDescriptor = fileDescriptor;
    }

    public DescriptorID<I> getId() {
        return id;
    }

    public FileDescriptor<T> getFileDescriptor() {
        return fileDescriptor;
    }

    public void setFileDescriptor(FileDescriptor<T> fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpenedFileDescriptor<?, ?> that = (OpenedFileDescriptor<?, ?>) o;

        if (!id.equals(that.id)) return false;
        return fileDescriptor != null ? fileDescriptor.equals(that.fileDescriptor) : that.fileDescriptor == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "OpenedFileDescriptor{" +
                "id=" + id +
                ", fileDescriptor=" + fileDescriptor +
                '}';
    }
}
