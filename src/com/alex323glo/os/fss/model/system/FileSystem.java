package com.alex323glo.os.fss.model.system;

import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.model.descriptor.DescriptorID;
import com.alex323glo.os.fss.model.descriptor.DescriptorIDGenerator;
import com.alex323glo.os.fss.model.descriptor.FileDescriptor;
import com.alex323glo.os.fss.model.descriptor.OpenedFileDescriptor;
import com.alex323glo.os.fss.model.file.*;

import static com.alex323glo.os.fss.utils.Validator.checkIfNull;
import static com.alex323glo.os.fss.utils.Validator.isNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO add doc
 */
public class FileSystem<T, I> implements Serializable {
    // TODO finish
    private Map<DescriptorID<T>, FileDescriptor<T>> fileDescriptorMap;
    private Map<DescriptorID<I>, OpenedFileDescriptor<I, T>> openedFileDescriptorMap;
    private DescriptorIDGenerator<T> descriptorIDGenerator;
    private DescriptorIDGenerator<I> openedFileDescriptorIdGenerator;

    /**
     * Initial constructor.
     */
    public FileSystem(DescriptorIDGenerator<T> descriptorIDGenerator,
                      DescriptorIDGenerator<I> openedFileDescriptorIdGenerator) {

        checkIfNull(descriptorIDGenerator, "descriptorIDGenerator");    // throws NullPointerException!
        checkIfNull(openedFileDescriptorIdGenerator,
                "openedFileDescriptorIdGenerator"); // throws NullPointerException!
        this.descriptorIDGenerator = descriptorIDGenerator;
        this.openedFileDescriptorIdGenerator = openedFileDescriptorIdGenerator;

        fileDescriptorMap = new HashMap<>();
        openedFileDescriptorMap = new HashMap<>();
    }

    /**
     * Extended initial constructor.
     *
     * @param fileDescriptorMap initial field value.
     * @param openedFileDescriptorMap initial field value.
     *
     * @see DescriptorID
     * @see FileDescriptor
     * @see OpenedFileDescriptor
     * @see Map
     */
    public FileSystem(Map<DescriptorID<T>, FileDescriptor<T>> fileDescriptorMap,
                      Map<DescriptorID<I>, OpenedFileDescriptor<I, T>> openedFileDescriptorMap,
                      DescriptorIDGenerator<T> descriptorIDGenerator,
                      DescriptorIDGenerator<I> openedFileDescriptorIdGenerator) {
        checkIfNull(fileDescriptorMap, "fileDescriptorMap");    // throws NullPointerException !
        checkIfNull(openedFileDescriptorMap, "openedFileDescriptorMap");    // throws NullPointerException !
        checkIfNull(descriptorIDGenerator, "descriptorIDGenerator");    // throws NullPointerException !
        checkIfNull(openedFileDescriptorIdGenerator,
                "openedFileDescriptorIdGenerator"); // throws NullPointerException!

        this.fileDescriptorMap = fileDescriptorMap;
        this.openedFileDescriptorMap = openedFileDescriptorMap;
        this.descriptorIDGenerator = descriptorIDGenerator;
        this.openedFileDescriptorIdGenerator = openedFileDescriptorIdGenerator;
    }

    /**
     * FileDescriptorMap field getter.
     *
     * @return field value.
     *
     * @see DescriptorID
     * @see FileDescriptor
     * @see Map
     */
    public Map<DescriptorID<T>, FileDescriptor<T>> getFileDescriptorMap() {
        return fileDescriptorMap;
    }

    /**
     * FileDescriptorMap field setter.
     *
     * @param fileDescriptorMap initial field value.
     *
     * @see DescriptorID
     * @see FileDescriptor
     * @see Map
     */
    public void setFileDescriptorMap(Map<DescriptorID<T>, FileDescriptor<T>> fileDescriptorMap) {
        checkIfNull(fileDescriptorMap, "fileDescriptorMap");    // throws NullPointerException !
        this.fileDescriptorMap = fileDescriptorMap;
    }

    /**
     * OpenedFileDescriptorMap field getter.
     *
     * @return field value.
     *
     * @see DescriptorID
     * @see OpenedFileDescriptor
     * @see Map
     */
    public Map<DescriptorID<I>, OpenedFileDescriptor<I, T>> getOpenedFileDescriptorMap() {
        return openedFileDescriptorMap;
    }

    /**
     * OpenedFileDescriptorMap field setter.
     *
     * @param openedFileDescriptorMap initial field value.
     *
     * @see DescriptorID
     * @see OpenedFileDescriptor
     * @see Map
     */
    public void setOpenedFileDescriptorMap(Map<DescriptorID<I>, OpenedFileDescriptor<I, T>> openedFileDescriptorMap) {
        checkIfNull(openedFileDescriptorMap, "openedFileDescriptorMap");    // throws NullPointerException !
        this.openedFileDescriptorMap = openedFileDescriptorMap;
    }

    /**
     * DescriptorIdGenerator field getter.
     *
     * @return field value.
     *
     * @see DescriptorIDGenerator
     */
    public DescriptorIDGenerator<T> getDescriptorIDGenerator() {
        return descriptorIDGenerator;
    }

    /**
     * DescriptorIdGenerator field setter.
     *
     * @param descriptorIDGenerator initial field value.
     *
     * @see DescriptorIDGenerator
     */
    public void setDescriptorIDGenerator(DescriptorIDGenerator<T> descriptorIDGenerator) {
        this.descriptorIDGenerator = descriptorIDGenerator;
    }

    /**
     * OpenedFileDescriptorIdGenerator field getter.
     *
     * @return field value.
     *
     * @see DescriptorIDGenerator
     */
    public DescriptorIDGenerator<I> getOpenedFileDescriptorIdGenerator() {
        return openedFileDescriptorIdGenerator;
    }

    /**
     * OpenedFileDescriptorIdGenerator field setter.
     *
     * @param openedFileDescriptorIdGenerator initial field value.
     *
     * @see DescriptorIDGenerator
     */
    public void setOpenedFileDescriptorIdGenerator(DescriptorIDGenerator<I> openedFileDescriptorIdGenerator) {
        this.openedFileDescriptorIdGenerator = openedFileDescriptorIdGenerator;
    }

    /**
     * TODO add doc.
     *
     * @param filename
     * @return
     * @throws FileSystemException
     */
    public FileDescriptor<T> addFile(String filename) throws FileSystemException {
        checkIfNull(filename, "filename");  // throws NullPointerException !

        for (Map.Entry<DescriptorID<T>, FileDescriptor<T>> entryElement: fileDescriptorMap.entrySet()) {
            // When free FileDescriptor was found:
            if (isNull(entryElement.getValue().getAbstractFile())) {
                entryElement.getValue().setAbstractFile(new File<>(filename, entryElement.getValue()));
                return entryElement.getValue();
            }
        }

        // When all FileDescriptors in current FileSystem are busy (in use):
        throw new FileSystemException("no unused FileDescriptors in mounted FileSystem");
    }

    public File<T> deleteFile(String name) throws FileSystemException {
        checkIfNull(name, "name");  // throws NullPointerException !

        for (Map.Entry<DescriptorID<T>, FileDescriptor<T>> entryElement: fileDescriptorMap.entrySet()) {
            // When needed File was found:
            if (!isNull(entryElement.getValue().getAbstractFile()) &&
                    entryElement.getValue().getAbstractFile().getName().equals(name) &&
                    entryElement.getValue().getAbstractFile().getType().equals(FileType.FILE)) {

                File<T> removedFile = (File<T>) entryElement.getValue().getAbstractFile();
                entryElement.getValue().setAbstractFile(null);
                return removedFile;
            }
        }

        // When FileSystem doesn't contain needed File:
        throw new FileSystemException("current FileSystem doesn't contain File with name \"" + name + "\"");
    }

    /**
     * TODO add doc.
     *
     * @param descriptorID
     * @return
     * @throws FileSystemException
     */
    public FileDescriptor<T> getFileDescriptor(DescriptorID<T> descriptorID) throws FileSystemException {
        checkIfNull(descriptorID, "descriptorID");  // throws NullPointerException !

        if (!fileDescriptorMap.containsKey(descriptorID)) {
            throw new FileSystemException("no FileDescriptor with key \"" + descriptorID + "\" in mounted FileSystem");
        }

        return fileDescriptorMap.get(descriptorID);
    }

    /**
     * TODO add doc.
     *
     * @param fileName
     * @return
     * @throws FileSystemException
     */
    public OpenedFileDescriptor<I, T> createOpenedFileDescriptor(String fileName) throws FileSystemException {
        checkIfNull(fileName, "fileName");

        FileDescriptor<T> searchedFileDescriptor = null;
        for (Map.Entry<DescriptorID<T>, FileDescriptor<T>> entry: fileDescriptorMap.entrySet()) {

            if (!isNull(entry.getValue().getAbstractFile()) &&
                    entry.getValue().getAbstractFile().getName().equals(fileName)) {
                searchedFileDescriptor = entry.getValue();
                break;
            }

        }

        if (isNull(searchedFileDescriptor)) {
            throw new FileSystemException("mounted FileSystem doesn't contain File with name \"" + fileName + "\"");
        }

        OpenedFileDescriptor<I, T> newOpenedFileDescriptor = new OpenedFileDescriptor<>(
                        openedFileDescriptorIdGenerator.generate(openedFileDescriptorMap.keySet()),
                        searchedFileDescriptor
        );

        openedFileDescriptorMap.put(newOpenedFileDescriptor.getId(), newOpenedFileDescriptor);
        return newOpenedFileDescriptor;
    }

    /**
     * TODO add doc.
     *
     * @param openedFileDescriptorId
     * @return
     * @throws FileSystemException
     */
    public OpenedFileDescriptor<I, T> deleteOpenedFileDescriptor(DescriptorID<I> openedFileDescriptorId)
            throws FileSystemException {

        if (openedFileDescriptorMap.isEmpty()) {
            throw new FileSystemException("no OpenedFileDescriptors stored in current FileSystem");
        }

        if (!openedFileDescriptorMap.containsKey(openedFileDescriptorId)) {
            throw new FileSystemException("no OpenedFileDescriptor stored in current FileSystem by such key");
        }

        return openedFileDescriptorMap.remove(openedFileDescriptorId);
    }

    /**
     * TODO add doc.
     *
     * @param openedFileDescriptorId
     * @param from
     * @param size
     * @return
     * @throws FileSystemException
     */
    public List<FileBlock> getFileBlocks(DescriptorID<I> openedFileDescriptorId, int from, int size)
            throws FileSystemException {

        if (from < 0 || size < 0) {
            throw new FileSystemException("from or/and size are less then 0.");
        }

        if (openedFileDescriptorMap.isEmpty()) {
            throw new FileSystemException("no OpenedFileDescriptors stored in current FileSystem");
        }

        if (!openedFileDescriptorMap.containsKey(openedFileDescriptorId)) {
            throw new FileSystemException("no OpenedFileDescriptor stored in current FileSystem by such key");
        }

        List<FileBlock> openedFileBlockList = openedFileDescriptorMap
                .get(openedFileDescriptorId)
                .getFileDescriptor()
                .getAbstractFile()
                .getFileBlocks();

        if (from >= openedFileBlockList.size() || from + size - 1 >= openedFileBlockList.size()) {
            throw new FileSystemException("from or/and size are wrong for such number of FileBlocks (" +
                    openedFileBlockList.size() + ")");
        }

        return openedFileBlockList.subList(from, from + size);
    }

    /**
     * TODO add doc.
     *
     * @param openedFileDescriptorId
     * @param fileBlocks
     * @param from
     * @param size
     * @throws FileSystemException
     */
    public void writeFileBlocks(DescriptorID<I> openedFileDescriptorId, List<FileBlock> fileBlocks, int from, int size)
            throws FileSystemException {
        if (from < 0 || size < 0) {
            throw new FileSystemException("from or/and size are less then 0.");
        }

        if (openedFileDescriptorMap.isEmpty()) {
            throw new FileSystemException("no OpenedFileDescriptors stored in current FileSystem");
        }

        if (!openedFileDescriptorMap.containsKey(openedFileDescriptorId)) {
            throw new FileSystemException("no OpenedFileDescriptor stored in current FileSystem by such key");
        }

        List<FileBlock> openedFileBlockList = openedFileDescriptorMap
                .get(openedFileDescriptorId)
                .getFileDescriptor()
                .getAbstractFile()
                .getFileBlocks();

        List<FileBlock> resultFileBlockList = new ArrayList<>(from + size - 1);
        for (int i = 0; i < from; i++) {
            resultFileBlockList.add(
                    i < openedFileBlockList.size() ? openedFileBlockList.get(i) : new FileBlock()
            );
        }
        for (int i = 0; i < size; i++) {
            resultFileBlockList.add(i + from,
                    i < fileBlocks.size() ? fileBlocks.get(i) :
                            i < openedFileBlockList.size() ? openedFileBlockList.get(i) :
                                    new FileBlock());
        }

        openedFileDescriptorMap
                .get(openedFileDescriptorId)
                .getFileDescriptor()
                .getAbstractFile()
                .setFileBlocks(resultFileBlockList);
    }

    /**
     * TODO add doc.
     *
     * @param fileName
     * @param linkName
     * @return
     * @throws FileSystemException
     */
    public FileDescriptor<T> addFileLink(String fileName, String linkName) throws FileSystemException {
        checkIfNull(fileName, "fileName");  // throws NullPointerException !
        checkIfNull(linkName, "linkName");  // throws NullPointerException !

        FileDescriptor<T> freeDescriptor = null;
        for (Map.Entry<DescriptorID<T>, FileDescriptor<T>> entryElement: fileDescriptorMap.entrySet()) {
            // When free FileDescriptor was found:
            if (isNull(entryElement.getValue().getAbstractFile())) {
                freeDescriptor = entryElement.getValue();
            }
        }
        // When all FileDescriptors in current FileSystem are busy (in use):
        if (isNull(freeDescriptor)) {
            throw new FileSystemException("no unused FileDescriptors in mounted FileSystem");
        }

        for (Map.Entry<DescriptorID<T>, FileDescriptor<T>> entryElement: fileDescriptorMap.entrySet()) {
            // When AbstractFile with needed fileName was found in current FileSystem:
            if (!isNull(entryElement.getValue().getAbstractFile()) &&
                    entryElement.getValue().getAbstractFile().getName().equals(fileName)) {

                freeDescriptor.setAbstractFile(
                        new FileLink<T>(linkName, freeDescriptor, entryElement.getValue().getAbstractFile()));
                return freeDescriptor;
            }
        }

        // When current FileSystem doesn't contain AbstractFile with such fileName:
        throw new FileSystemException("no AbstractFile with such fileName in current FileSystem");
    }

    /**
     * TODO add doc.
     *
     * @param name
     * @return
     * @throws FileSystemException
     */
    public FileLink<T> deleteFileLink(String name) throws FileSystemException {
        checkIfNull(name, "name");  // throws NullPointerException !

        for (Map.Entry<DescriptorID<T>, FileDescriptor<T>> entryElement: fileDescriptorMap.entrySet()) {
            // When needed FileLink was found:
            if (!isNull(entryElement.getValue().getAbstractFile()) &&
                    entryElement.getValue().getAbstractFile().getName().equals(name) &&
                    entryElement.getValue().getAbstractFile().getType().equals(FileType.LINK)) {

                FileLink<T> removedFileLink = (FileLink<T>) entryElement.getValue().getAbstractFile();
                entryElement.getValue().setAbstractFile(null);
                return removedFileLink;
            }
        }

        // When FileSystem doesn't contain needed FileLink:
        throw new FileSystemException("current FileSystem doesn't contain FileLink with name \"" + name + "\"");
    }

    /**
     * TODO add doc.
     *
     * @param name
     * @param newSize
     * @return
     * @throws FileSystemException
     */
    public List<FileBlock> resizeFile(String name, int newSize) throws FileSystemException {
        checkIfNull(name, "name");  // throws NullPointerException !

        if (newSize < 0) {
            throw new FileSystemException("newSize is less then 0");
        }

        AbstractFile<T> neededFile = null;
        for (Map.Entry<DescriptorID<T>, FileDescriptor<T>> entry: fileDescriptorMap.entrySet()) {
            // When needed File was found:
            if (!isNull(entry.getValue().getAbstractFile()) &&
                    entry.getValue().getAbstractFile().getName().equals(name) &&
                    entry.getValue().getAbstractFile().getType().equals(FileType.FILE)) {
                neededFile = entry.getValue().getAbstractFile();
            }
        }

        // When FileSystem doesn't contain needed File:
        if (isNull(neededFile)) {
            throw new FileSystemException("current FileSystem doesn't contain File with name \"" + name + "\"");
        }

        List<FileBlock> fileBlocks = neededFile.getFileBlocks();

        // When new size is equal to old:
        if (newSize == fileBlocks.size()) {
            return null;
        }

        // When new size is less then old:
        if (newSize < fileBlocks.size()) {
            neededFile.setFileBlocks(fileBlocks.subList(0, newSize));
            return fileBlocks.subList(newSize, fileBlocks.size());
        }

        // When new size greater then old:
        for (int i = 0; i < newSize - fileBlocks.size(); i++) {
            fileBlocks.add(new FileBlock());
        }
        return null;
    }
}
