package com.alex323glo.os.fss.controller;

import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.loader.FileSystemLoader;
import com.alex323glo.os.fss.model.descriptor.DescriptorID;
import com.alex323glo.os.fss.model.descriptor.DescriptorIDGenerator;
import com.alex323glo.os.fss.model.descriptor.FileDescriptor;
import com.alex323glo.os.fss.model.descriptor.OpenedFileDescriptor;
import com.alex323glo.os.fss.model.file.AbstractFile;
import com.alex323glo.os.fss.model.file.File;
import com.alex323glo.os.fss.model.file.FileBlock;
import com.alex323glo.os.fss.model.file.FileLink;
import com.alex323glo.os.fss.model.system.FileSystem;

import static com.alex323glo.os.fss.utils.Validator.isNull;
import static com.alex323glo.os.fss.utils.Validator.checkIfNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO add doc.
 */
public class MenuControllerImpl<T, I> implements MenuController<T, I> {

    private FileSystem<T, I> mountedFileSystem;
    private FileSystemLoader<T, I> fileSystemLoader;

    /**
     * Initial constructor.
     *
     * @param fileSystemLoader initial field value.
     *
     * @see FileSystemLoader
     */
    public MenuControllerImpl(FileSystemLoader<T, I> fileSystemLoader) {
        checkIfNull(fileSystemLoader, "fileSystemLoader");  // throws NullPointerException !
        this.fileSystemLoader = fileSystemLoader;
    }

    /**
     * Extended initial constructor.
     *
     * @param mountedFileSystem initial field value.
     * @param fileSystemLoader initial field value.
     *
     * @see FileSystem
     * @see FileSystemLoader
     */
    public MenuControllerImpl(FileSystem<T, I> mountedFileSystem, FileSystemLoader<T, I> fileSystemLoader) {
        checkIfNull(mountedFileSystem, "mountedFileSystem");    // throws NullPointerException !
        checkIfNull(fileSystemLoader, "fileSystemLoader");  // throws NullPointerException !

        this.mountedFileSystem = mountedFileSystem;
        this.fileSystemLoader = fileSystemLoader;
    }

    /**
     * MountedFileSystem field getter.
     *
     * @return field value.
     *
     * @see FileSystem
     */
    public FileSystem<T, I> getMountedFileSystem() {
        return mountedFileSystem;
    }

    /**
     * MountedFileSystem field setter.
     *
     * @param mountedFileSystem initial field value.
     *
     * @see FileSystem
     */
    public void setMountedFileSystem(FileSystem<T, I> mountedFileSystem) {
        checkIfNull(mountedFileSystem, "mountedFileSystem");    // throws NullPointerException !
        this.mountedFileSystem = mountedFileSystem;
    }

    /**
     * FileSystemLoader field getter.
     *
     * @return field value.
     *
     * @see FileSystemLoader
     */
    public FileSystemLoader<T, I> getFileSystemLoader() {
        return fileSystemLoader;
    }

    /**
     * FileSystemLoader field setter.
     *
     * @param fileSystemLoader initial field value.
     *
     * @see FileSystemLoader
     */
    public void setFileSystemLoader(FileSystemLoader<T, I> fileSystemLoader) {
        checkIfNull(fileSystemLoader, "fileSystemLoader");  // throws NullPointerException !
        this.fileSystemLoader = fileSystemLoader;
    }

    /**
     * TODO add doc.
     *
     * @return
     */
    @Override
    public DescriptorIDGenerator<T> getDescriptorIdGenerator() throws FileSystemException {
        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        return mountedFileSystem.getDescriptorIDGenerator();
    }

    /**
     * TODO add doc.
     *
     * @return
     */
    @Override
    public DescriptorIDGenerator<I> getOpenedFileDescriptorIdGenerator() throws FileSystemException {
        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        return mountedFileSystem.getOpenedFileDescriptorIdGenerator();
    }

    /**
     * Gets mounted FileSystem.
     *
     * @return ref to mounted FileSystem, or null, if
     * FileSystem is not mounted yet.
     */
    @Override
    public FileSystem<T, I> getFileSystem() {
        return mountedFileSystem;
    }

    /**
     * Mounts FileSystem, stored in file. Realisation of "mount"
     * command in console menu.
     *
     * @param filePath path to file, where FileSystem is stored.
     * @return reference to mounted FileSystem.
     * @throws FileSystemException when FileSystem can't be mounted or
     *                             filePath is wrong.
     * @see FileSystem
     * @see FileSystemException
     */
    @Override
    public FileSystem<T, I> mountFileSystem(String filePath) throws FileSystemException {
        checkIfNull(filePath, "filePath");  // throws NullPointerException !

        mountedFileSystem = fileSystemLoader.load(filePath);
        return mountedFileSystem;
    }

    /**
     * Unmounts FileSystem from application. Realisation of "umount"
     * command in console menu.
     *
     * @return reference to unmounted FileSystem.
     * @throws FileSystemException when FileSystem can't be unmounted.
     * @see FileSystem
     * @see FileSystemException
     */
    @Override
    public FileSystem<T, I> unmountFileSystem() throws FileSystemException {
        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        FileSystem<T, I> unmountedFileSystem = mountedFileSystem;
        mountedFileSystem = null;   // unmounts mounted FileSystem
        return unmountedFileSystem;
    }

    /**
     * Gets data of concrete FileDescriptor in mounted FileSystem.
     * Realisation of "filestat id" command in console menu.
     *
     * @param id unique ID of needed FileDescriptor.
     * @return needed FileDescriptor or null, if FileDescriptor
     * with such ID doesn't exist in mounted FileSystem.
     * @throws FileSystemException when mounted FileSystem can't search for
     *                             needed FileDescriptor.
     * @see FileDescriptor
     * @see DescriptorID
     * @see T
     * @see FileSystemException
     */
    @Override
    public FileDescriptor<T> getDescriptor(DescriptorID<T> id) throws FileSystemException {
        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        checkIfNull(id, "id");  // throws NullPointerException !

        return mountedFileSystem.getFileDescriptor(id);
    }

    /**
     * Gets List of all Files in mounted FileSystem. Realisation of
     * "ls" command in console menu.
     *
     * @return List of files stored in mounted FileSystem.
     * @throws FileSystemException when mounted FileSystem can't
     *                             give List of stored Files.
     * @see File
     * @see List
     * @see FileSystemException
     */
    @Override
    public List<AbstractFile<T>> getAllFiles() throws FileSystemException {
        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        Map<DescriptorID<T>, FileDescriptor<T>> fileDescriptorMap = mountedFileSystem.getFileDescriptorMap();
        if (isNull(fileDescriptorMap)) {
            throw new FileSystemException("mounted FileSystem doesn't contain any FileDescriptors");
        }

        List<AbstractFile<T>> fileList = new ArrayList<>(fileDescriptorMap.values().size());
        fileDescriptorMap.forEach((key, value) -> {
            if (!isNull(value.getAbstractFile())) {
                fileList.add(value.getAbstractFile());
            }
        });

        if (fileList.isEmpty()) {
            throw new FileSystemException("mounted FileSystem doesn't contain any AbstractFile");
        }

        return fileList;
    }

    /**
     * Creates new File in mounted FileSystem. Realisation of
     * "create name" command in console menu.
     *
     * @param name String name of new File.
     * @return reference to created File in mounted FileSystem.
     * @throws FileSystemException when mounted FileSystem can't
     *                             create and store new File.
     * @see File
     * @see FileSystemException
     */
    @Override
    public File<T> createFile(String name) throws FileSystemException {
        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        checkIfNull(name, "name");  // throws NullPointerException !

        // Creates simple File without chain of Links:
        return (File<T>) mountedFileSystem.addFile(name).getAbstractFile();
    }

    /**
     * Removes concrete File from mounted FileSystem. Realisation of
     * "remove name" command in console menu.
     *
     * @param name String name of File, needed to be removed from mounted FileSystem.
     * @return reference to removed from mounted FileSystem File.
     * @throws FileSystemException when mounted FileSystem can't remove concrete File.
     * @see File
     * @see T
     * @see FileSystemException
     */
    @Override
    public File<T> removeFile(String name) throws FileSystemException {
        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        checkIfNull(name, "name");  // throws NullPointerException !

        return mountedFileSystem.deleteFile(name);
    }

    /**
     * Creates new OpenedFileDescriptor in mounted FileSystem for
     * concrete File in mounted FileSystem ("opens" needed File).
     * Realisation of "open name" command in console menu.
     *
     * @param name String name of File, needed to be "opened".
     * @return created and stored in mounted FileSystem OpenedFileDescriptor.
     * @throws FileSystemException when mounted FileSystem can't create new
     *                             OpenedFileDescriptor for stored File or name is wrong.
     * @see OpenedFileDescriptor
     * @see I
     * @see T
     * @see FileSystemException
     */
    @Override
    public OpenedFileDescriptor<I, T> openFile(String name) throws FileSystemException {
        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        checkIfNull(name, "name");  // throws NullPointerException !

        return mountedFileSystem.createOpenedFileDescriptor(name);
    }

    /**
     * Removes concrete OpenedFileDescriptor from mounted FileSystem ("closes" needed File).
     * Realisation of "close fd" command in console menu.
     *
     * @param openedFileDescriptorID ID of OpenedFileDescriptor, needed to be removed.
     * @return removed from mounted FileSystem OpenedFileDescriptor.
     * @throws FileSystemException when mounted FileSystem can't find and remove
     *                             needed OpenedFileDescriptor.
     * @see OpenedFileDescriptor
     * @see DescriptorID
     * @see I
     * @see T
     * @see FileSystemException
     */
    @Override
    public OpenedFileDescriptor<I, T> closeFile(DescriptorID<I> openedFileDescriptorID) throws FileSystemException {
        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        checkIfNull(openedFileDescriptorID, "openedFileDescriptorID");  // throws NullPointerException !

        return mountedFileSystem.deleteOpenedFileDescriptor(openedFileDescriptorID);
    }

    /**
     * Gets List of FileBlocks from needed File, opened with openedFileDescriptorID ("reads"
     * needed File). Realisation of "read fd from size" command in console menu.
     *
     * @param openedFileDescriptorID ID of OpenedFileDescriptor of needed File in
     *                               mounted FileSystem.
     * @param from                   position (index) of first FileBlock in File, needed to be "read".
     * @param size                   number of FileBlocks, needed to be "read" from File.
     * @return List of needed FileBlocks of needed File.
     * @throws FileSystemException when mounted FileSystem can't read FileBlocks from needed
     *                             stored File.
     * @see FileBlock
     * @see List
     * @see DescriptorID
     * @see I
     * @see FileSystemException
     */
    @Override
    public List<FileBlock> readFile(DescriptorID<I> openedFileDescriptorID, Integer from, Integer size)
            throws FileSystemException {

        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        checkIfNull(openedFileDescriptorID, "openedFileDescriptorID");  // throws NullPointerException !
        checkIfNull(from, "from");  // throws NullPointerException !
        checkIfNull(size, "size");  // throws NullPointerException !

        return mountedFileSystem.getFileBlocks(openedFileDescriptorID, from, size);
    }

    /**
     * Sets list of FileBlocks to needed File, opened with openedFileDescriptorID ("writes"
     * needed File). Realisation of "write fd from size" command in console menu.
     *
     * @param openedFileDescriptorID ID of OpenedFileDescriptor of needed File in
     *                               mounted FileSystem.
     * @param data                   List of FileBlocks, needed to be "wrote" to needed File.
     * @param from                   position (index) of first FileBlock in File, needed to be "wrote".
     * @param size                   number of FileBlocks, needed to be "wrote" to File.
     * @throws FileSystemException when mounted FileSystem can't write FileBlocks to needed
     *                             stored File.
     * @see DescriptorID
     * @see I
     * @see FileBlock
     * @see List
     * @see FileSystemException
     */
    @Override
    public void writeFile(DescriptorID<I> openedFileDescriptorID, List<FileBlock> data, Integer from, Integer size)
            throws FileSystemException {

        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        checkIfNull(openedFileDescriptorID, "openedFileDescriptorID");  // throws NullPointerException !
        checkIfNull(data, "data");  // throws NullPointerException !
        checkIfNull(from, "from");  // throws NullPointerException !
        checkIfNull(size, "size");  // throws NullPointerException !

        mountedFileSystem.writeFileBlocks(openedFileDescriptorID, data, from, size);
    }

    /**
     * Creates new FileLink in mounted FileSystem to File, stored in mounted FileSystem.
     * Realisation of "link name1 name2" command in console menu.
     *
     * @param fileName String name of File in mounted FileSystem, needed to be "linked" with FileLink.
     * @param linkName String name of new FileLink in mounted FileSystem, needed to be created.
     * @return reference to created in mounted FileSystem FileLink to needed File.
     * @throws FileSystemException when mounted FileSystem can't create new FileLink to
     *                             stored File.
     * @see FileLink
     * @see T
     * @see FileSystemException
     */
    @Override
    public FileLink<T> createFileLink(String fileName, String linkName) throws FileSystemException {
        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        checkIfNull(fileName, "fileName");  // throws NullPointerException !
        checkIfNull(linkName, "linkName");  // throws NullPointerException !

        // Creates simple FileLink:
        return (FileLink<T>) mountedFileSystem.addFileLink(fileName, linkName).getAbstractFile();
    }

    /**
     * Removes concrete FileLink from mounted FileSystem. Realisation of
     * "unlink name" command in console menu.
     *
     * @param name String name of FileLink, needed to be removed from mounted FileSystem.
     * @return reference to removed from mounted FileSystem FileLink.
     * @throws FileSystemException when mounted FileSystem can't remove concrete FileLink.
     * @see FileLink
     * @see T
     * @see FileSystemException
     */
    @Override
    public FileLink<T> removeFileLink(String name) throws FileSystemException {

        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        checkIfNull(name, "name");  // throws NullPointerException !

        return mountedFileSystem.deleteFileLink(name);
    }

    /**
     * Changes number of FileBlocks of concrete File in mounted FileSystem ("resizes"
     * needed File). Realisation of "truncate name size" command in console menu.
     *
     * @param name    String name of File, needed to be "resized".
     * @param newSize new number of FileBlocks of needed File.
     * @return List of removed FileBlocks (if new size is less then old size), or null
     * (if new size is bigger or equal to old size).
     * @throws FileSystemException when mounted FileSystem can't resize needed stored File.
     * @see FileBlock
     * @see List
     * @see FileSystemException
     */
    @Override
    public List<FileBlock> resizeFile(String name, Integer newSize) throws FileSystemException {

        if (isNull(mountedFileSystem)) {
            throw new FileSystemException("FileSystem is not mounted");
        }

        checkIfNull(name, "name");  // throws NullPointerException !
        checkIfNull(newSize, "newSize");    // throws NullPointerException !

        return mountedFileSystem.resizeFile(name, newSize);
    }
}
