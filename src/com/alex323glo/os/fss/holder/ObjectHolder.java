package com.alex323glo.os.fss.holder;

import java.util.*;

/**
 * Global object holder class ("Singleton" and "Object Holder" patterns).
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class ObjectHolder {

    private static ObjectHolder singleton;

    private Map<String, Object> objectMap;

    /**
     * Private constructor.
     */
    private ObjectHolder() {
        objectMap = new HashMap<>();
    }

    /**
     * Gets needed object from ObjectHolder by its name.
     *
     * @param objectName name of needed object.
     * @return needed object, or null, if ObjectHolder doesn't contain
     * object with such name.
     */
    public Object get(String objectName) {
        if (objectName == null) {
            throw new NullPointerException("objectName is null");
        }

        return objectMap.get(objectName);
    }

    /**
     * Adds object to ObjectHolder by its name.
     *
     * @param objectName name of object, needed to store.
     * @param object object, needed to store.
     * @return object, stored by such name, or null, if ObjectHolder
     * doesn't contain object with such name.
     */
    public Object put(String objectName, Object object) {
        if (objectName == null) {
            throw new NullPointerException("objectName is null");
        }

        return objectMap.put(objectName, object);
    }

    /**
     * Gets Set of all objects' names (key Set).
     *
     * @return Set on String (unique) object names.
     *
     * @see Set
     */
    public Set<String> allObjectNames() {
        return objectMap.keySet();
    }

    /**
     * Gets List of all objects, stored in ObjectHolder.
     *
     * @return List of stored objects.
     *
     * @see List
     */
    public List<Object> allObjects() {
        return new ArrayList<>(objectMap.values());
    }

    /**
     * Gets reference to single-existing instance of ObjectHolder
     * It creates new instance only once - when this method is
     * called first time.
     *
     * @return single-existing instance of ObjectHolder
     */
    public static ObjectHolder getInstance() {
        if (singleton == null) {
            singleton = new ObjectHolder();
        }

        return singleton;
    }

}
