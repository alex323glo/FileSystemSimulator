package com.alex323glo.os.fss.model.file;

/**
 * TODO add doc
 */
// TODO finish
public class FileBlock {
    private static long idCounter = 0;

    private String id;
    private String data;

    public FileBlock() {
        id = "" + (idCounter++);
    }

    public FileBlock(String data) {
        id = "" + (idCounter++);
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        FileBlock fileBlock = (FileBlock) object;

        if (!id.equals(fileBlock.id)) return false;
        return data != null ? data.equals(fileBlock.data) : fileBlock.data == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
