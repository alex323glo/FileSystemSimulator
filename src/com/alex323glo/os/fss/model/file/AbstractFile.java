package com.alex323glo.os.fss.model.file;

import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.model.descriptor.FileDescriptor;

import java.util.List;

/**
 * TODO add doc.
 */
public abstract class AbstractFile<T> {

    protected String name;
    protected FileDescriptor<T> descriptor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileDescriptor<T> getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(FileDescriptor<T> descriptor) {
        this.descriptor = descriptor;
    }

    public abstract FileType getType();

    public abstract List<FileBlock> getFileBlocks() throws FileSystemException;

    public abstract void setFileBlocks(List<FileBlock> fileBlocks) throws FileSystemException;

}
