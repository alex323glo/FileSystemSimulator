package com.alex323glo.os.fss.model.file;

import com.alex323glo.os.fss.model.descriptor.FileDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO add doc
 */
public class File<T> extends AbstractFile<T> {

    private List<FileBlock> fileBlocks;

    public File() {
    }

    public File(String name, FileDescriptor<T> descriptor) {
        if (name == null || descriptor == null) {
            throw new NullPointerException("name or descriptor is null");
        }

        this.name = name;
        this.descriptor = descriptor;
        fileBlocks = new ArrayList<>();
    }

    public File(String name, FileDescriptor<T> descriptor, List<FileBlock> fileBlocks) {
        if (name == null || descriptor == null || fileBlocks == null) {
            throw new NullPointerException("name, descriptor or fileBlocks is null");
        }

        this.name = name;
        this.descriptor = descriptor;
        this.fileBlocks = fileBlocks;
    }

    @Override
    public FileType getType() {
        return FileType.FILE;
    }

    @Override
    public List<FileBlock> getFileBlocks() {
        return fileBlocks;
    }

    @Override
    public void setFileBlocks(List<FileBlock> fileBlocks) {
        this.fileBlocks = fileBlocks;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", descriptor=" + descriptor.getId() +
                ", fileBlocks=" + fileBlocks +
                '}';
    }
}
