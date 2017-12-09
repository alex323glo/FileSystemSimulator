package com.alex323glo.os.fss.exception;

import com.alex323glo.os.fss.model.system.FileSystem;

/**
 * File System operations execution exception. Caused by some
 * problems during work with FileSystem.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see FileSystem
 */
public class FileSystemException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public FileSystemException(String message) {
        super(message);
    }
}
