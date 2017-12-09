package com.alex323glo.os.fss.controller;

import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.model.descriptor.DescriptorID;
import com.alex323glo.os.fss.model.descriptor.FileDescriptor;
import com.alex323glo.os.fss.model.descriptor.OpenedFileDescriptor;
import com.alex323glo.os.fss.model.file.File;
import com.alex323glo.os.fss.model.file.FileBlock;
import com.alex323glo.os.fss.model.file.FileLink;
import com.alex323glo.os.fss.model.system.FileSystem;

import java.util.List;

/**
 * Controller of Console Menu business logic.
 *
 * @param <T> type of FileDescriptor ID value.
 * @param <I> type of OpenedFileDescriptor ID value.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public interface MenuController<T, I> {

    /**
     * Mounts FileSystem, stored in file. Realisation of "mount"
     * command in console menu.
     *
     * @param filePath path to file, where FileSystem is stored.
     * @return reference to mounted FileSystem.
     * @throws FileSystemException when FileSystem can't be mounted or
     * filePath is wrong.
     *
     * @see FileSystem
     * @see FileSystemException
     */
    FileSystem mountFileSystem(String filePath) throws FileSystemException;

    /**
     * Unmounts FileSystem from application. Realisation of "umount"
     * command in console menu.
     *
     * @return reference to unmounted FileSystem.
     * @throws FileSystemException when FileSystem can't be unmounted.
     *
     * @see FileSystem
     * @see FileSystemException
     */
    FileSystem unmountFileSystem() throws FileSystemException;

    /**
     * Gets data of concrete FileDescriptor in mounted FileSystem.
     * Realisation of "filestat id" command in console menu.
     *
     * @param id unique ID of needed FileDescriptor.
     * @return needed FileDescriptor or null, if FileDescriptor
     * with such ID doesn't exist in mounted FileSystem.
     * @throws FileSystemException when mounted FileSystem can't search for
     * needed FileDescriptor.
     *
     * @see FileDescriptor
     * @see DescriptorID
     * @see T
     * @see FileSystemException
     * */
    FileDescriptor<T> getDescriptor(DescriptorID<T> id) throws FileSystemException;

    /**
     * Gets List of all Files in mounted FileSystem. Realisation of
     * "ls" command in console menu.
     *
     * @return List of files stored in mounted FileSystem.
     * @throws FileSystemException when mounted FileSystem can't
     * give List of stored Files.
     *
     * @see File
     * @see List
     * @see FileSystemException
     */
    List<File<T>> getAllFiles() throws FileSystemException;

    /**
     * Creates new File in mounted FileSystem. Realisation of
     * "create name" command in console menu.
     *
     * @param name String name of new File.
     * @return reference to created File in mounted FileSystem.
     * @throws FileSystemException when mounted FileSystem can't
     * create and store new File.
     *
     * @see File
     * @see FileSystemException
     */
    File<T> createFile(String name) throws FileSystemException;

    /**
     * Creates new OpenedFileDescriptor in mounted FileSystem for
     * concrete File in mounted FileSystem ("opens" needed File).
     * Realisation of "open name" command in console menu.
     *
     * @param name String name of File, needed to be "opened".
     * @return created and stored in mounted FileSystem OpenedFileDescriptor.
     * @throws FileSystemException when mounted FileSystem can't create new
     * OpenedFileDescriptor for stored File or name is wrong.
     *
     * @see OpenedFileDescriptor
     * @see I
     * @see T
     * @see FileSystemException
     */
    OpenedFileDescriptor<I, T> openFile(String name) throws FileSystemException;

    /**
     * Removes concrete OpenedFileDescriptor from mounted FileSystem ("closes" needed File).
     * Realisation of "close fd" command in console menu.
     *
     * @param openedFileDescriptorID ID of OpenedFileDescriptor, needed to be removed.
     * @return removed from mounted FileSystem OpenedFileDescriptor.
     * @throws FileSystemException when mounted FileSystem can't find and remove
     * needed OpenedFileDescriptor.
     *
     * @see OpenedFileDescriptor
     * @see DescriptorID
     * @see I
     * @see T
     * @see FileSystemException
     */
    OpenedFileDescriptor<I, T> closeFile(DescriptorID<I> openedFileDescriptorID) throws FileSystemException;

    /**
     * Gets List of FileBlocks from needed File, opened with openedFileDescriptorID ("reads"
     * needed File). Realisation of "read fd from size" command in console menu.
     *
     * @param openedFileDescriptorID ID of OpenedFileDescriptor of needed File in
     *                               mounted FileSystem.
     * @param from position (index) of first FileBlock in File, needed to be "read".
     * @param size number of FileBlocks, needed to be "read" from File.
     * @return List of needed FileBlocks of needed File.
     * @throws FileSystemException when mounted FileSystem can't read FileBlocks from needed
     * stored File.
     *
     * @see FileBlock
     * @see List
     * @see DescriptorID
     * @see I
     * @see FileSystemException
     */
    List<FileBlock> readFile(DescriptorID<I> openedFileDescriptorID, Long from, Long size)
            throws FileSystemException;

    /**
     * Sets list of FileBlocks to needed File, opened with openedFileDescriptorID ("writes"
     * needed File). Realisation of "write fd from size" command in console menu.
     *
     * @param openedFileDescriptorID ID of OpenedFileDescriptor of needed File in
     *                               mounted FileSystem.
     * @param data List of FileBlocks, needed to be "wrote" to needed File.
     * @param from position (index) of first FileBlock in File, needed to be "wrote".
     * @param size number of FileBlocks, needed to be "wrote" to File.
     * @throws FileSystemException when mounted FileSystem can't write FileBlocks to needed
     * stored File.
     *
     * @see DescriptorID
     * @see I
     * @see FileBlock
     * @see List
     * @see FileSystemException
     */
    void writeFile(DescriptorID<I> openedFileDescriptorID, List<FileBlock> data, Long from, Long size)
            throws FileSystemException;

    /**
     * Creates new FileLink in mounted FileSystem to File, stored in mounted FileSystem.
     * Realisation of "link name1 name2" command in console menu.
     *
     * @param fileName String name of File in mounted FileSystem, needed to be "linked" with FileLink.
     * @param linkName String name of new FileLink in mounted FileSystem, needed to be created.
     * @return reference to created in mounted FileSystem FileLink to needed File.
     * @throws FileSystemException when mounted FileSystem can't create new FileLink to
     * stored File.
     *
     * @see FileLink
     * @see T
     * @see FileSystemException
     */
    FileLink<T> createFileLink(String fileName, String linkName) throws FileSystemException;

    /**
     * Removes concrete FileLink from mounted FileSystem. Realisation of
     * "unlink name" command in console menu.
     *
     * @param name String name of FileLink, needed to be removed from mounted FileSystem.
     * @return reference to removed from mounted FileSystem FileLink.
     * @throws FileSystemException when mounted FileSystem can't remove concrete FileLink.
     *
     * @see FileLink
     * @see T
     * @see FileSystemException
     */
    FileLink<T> removeFileLink(String name) throws FileSystemException;

    /**
     * Changes number of FileBlocks of concrete File in mounted FileSystem ("resizes"
     * needed File). Realisation of "truncate name size" command in console menu.
     *
     * @param name String name of File, needed to be "resized".
     * @param newSize new number of FileBlocks of needed File.
     * @return List of removed FileBlocks (if new size is less then old size), or null
     * (if new size is bigger or equal to old size).
     * @throws FileSystemException when mounted FileSystem can't resize needed stored File.
     *
     * @see FileBlock
     * @see List
     * @see FileSystemException
     */
    List<FileBlock> resizeFile(String name, Long newSize) throws FileSystemException;

}
