package com.alex323glo.os.fss.model.file;

import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.model.descriptor.FileDescriptor;

import java.util.List;

/**
 * TODO add doc
 */
public class FileLink<T> extends AbstractFile<T> {

    private AbstractFile<T> target;

    public FileLink(String name, FileDescriptor<T> descriptor) {
        if (name == null || descriptor == null) {
            throw new NullPointerException("name or descriptor is null");
        }

        this.name = name;
        this.descriptor = descriptor;
    }

    public FileLink(String name, FileDescriptor<T> descriptor, AbstractFile<T> target) {
        if (name == null || descriptor == null || target == null) {
            throw new NullPointerException("name, descriptor or target is null");
        }

        this.name = name;
        this.descriptor = descriptor;
        this.target = target;
    }

    @Override
    public FileType getType() {
        return FileType.LINK;
    }

    @Override
    public List<FileBlock> getFileBlocks() throws FileSystemException {
        if (target == null) {
            throw new FileSystemException("this chain of Links ends with null (no target File)");
        }

        return target.getFileBlocks();
    }

    @Override
    public void setFileBlocks(List<FileBlock> fileBlocks) throws FileSystemException {
        if (target == null) {
            throw new FileSystemException("this chain of Links ends with null (no target File)");
        }

        target.setFileBlocks(fileBlocks);
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", descriptor=" + descriptor.getId() +
                ", target='" + target.getName() + '\'' +
                '}';
    }
}
