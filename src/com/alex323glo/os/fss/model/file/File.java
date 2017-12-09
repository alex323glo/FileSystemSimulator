package com.alex323glo.os.fss.model.file;

import com.alex323glo.os.fss.model.descriptor.FileDescriptor;

import java.util.List;

/**
 * TODO add doc
 */
public class File<T> {

    private String name;
    private FileDescriptor<T> descriptor;
    private List<FileBlock> fileBlocks;

    public File() {
    }

    public File(String name, FileDescriptor<T> descriptor) {
        if (name == null || descriptor == null) {
            throw new NullPointerException("name or descriptor is null");
        }

        this.name = name;
        this.descriptor = descriptor;
    }

    public File(String name, FileDescriptor<T> descriptor, List<FileBlock> fileBlocks) {
        this.name = name;
        this.descriptor = descriptor;
        this.fileBlocks = fileBlocks;
    }

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

    public List<FileBlock> getFileBlocks() {
        return fileBlocks;
    }

    public void setFileBlocks(List<FileBlock> fileBlocks) {
        this.fileBlocks = fileBlocks;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", descriptor=" + descriptor +
                ", fileBlocks=" + fileBlocks +
                '}';
    }
}
