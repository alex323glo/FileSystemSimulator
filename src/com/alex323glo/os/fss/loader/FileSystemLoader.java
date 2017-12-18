package com.alex323glo.os.fss.loader;

import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.model.system.FileSystem;

/**
 * FileSystem loader interface.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see FileSystem
 */
public interface FileSystemLoader<T, I> {

    /**
     * Load file system from source by String path.
     *
     * @param path String root to needed FileSystem location (storage).
     * @return needed exemplar of FileSystem.
     * @throws FileSystemException if can't find or/and load needed FileSystem by such path.
     *
     * @see FileSystem
     * @see FileSystemException
     */
    FileSystem<T, I> load(String path) throws FileSystemException;

}
