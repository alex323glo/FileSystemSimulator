package com.alex323glo.os.fss.loader;

import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.model.system.FileSystem;

import static com.alex323glo.os.fss.utils.Validator.checkIfNull;

import java.util.HashMap;
import java.util.Map;

/**
 * FileSystem loader implementation. Based on creating and storing FileSystem objects
 * (using Collections with Map interface) only in Runtime and cleaning them after Runtime ends.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see FileSystemLoader
 */
public class VirtualFileSystemLoader<T, I> implements FileSystemLoader<T, I> {

    private Map<String, FileSystem<T, I>> fileSystemMap;

    /**
     * Constructor.
     */
    public VirtualFileSystemLoader() {
        fileSystemMap = new HashMap<>();
    }

    /**
     * Initial constructor.
     *
     * @param fileSystemMap initial field value.
     *
     * @see FileSystem
     * @see Map
     */
    public VirtualFileSystemLoader(Map<String, FileSystem<T, I>> fileSystemMap) {
        checkIfNull(fileSystemMap, "fileSystemMap");    // throws NullPointerException !
        this.fileSystemMap = fileSystemMap;
    }

    /**
     * FileSystemMap field getter.
     *
     * @return field value.
     *
     * @see FileSystem
     * @see Map
     */
    public Map<String, FileSystem<T, I>> getFileSystemMap() {
        return fileSystemMap;
    }

    /**
     * FileSystemMap field getter.
     *
     * @param fileSystemMap initial field value.
     *
     * @see FileSystem
     * @see Map
     */
    public void setFileSystemMap(Map<String, FileSystem<T, I>> fileSystemMap) {
        checkIfNull(fileSystemMap, "fileSystemMap");    // throws NullPointerException !
        this.fileSystemMap = fileSystemMap;
    }

    /**
     * Load file system from source by String path.
     *
     * @param path String root to needed FileSystem location (storage).
     * @return needed exemplar of FileSystem.
     * @throws FileSystemException if can't find or/and load needed FileSystem by such path.
     * @see FileSystem
     * @see FileSystemException
     */
    @Override
    public FileSystem<T, I> load(String path) throws FileSystemException {
        checkIfNull(path, "path");  // throws NullPointerException !

        if (fileSystemMap.isEmpty()) {
            throw new FileSystemException("no FileSystems found (fileSystemMap is empty)");
        }

        if (!fileSystemMap.containsKey(path)) {
            throw new FileSystemException("no FileSystems found by path \"" + path + "\"");
        }

        return fileSystemMap.get(path);
    }
}
