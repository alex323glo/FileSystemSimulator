package com.alex323glo.os.fss.utils;

/**
 * Validation util tools container.
 * Contains logic for different validation tasks.
 *
 * @author alex323glo
 * @version 1.0.0
 */
public class Validator {

    /**
     * Checks param value if it is null (throws exception).
     *
     * @param object value, needed to check.
     * @param comment additional info about checked object for Exception message (could be null).
     * @throws NullPointerException if value is null.
     *
     * @see NullPointerException
     */
    public static void checkIfNull(Object object, String comment) throws NullPointerException {
        if (object == null) {
            throw new NullPointerException("object " + (comment != null ? "(" + comment + ") " : "") + "is null");
        }
    }

    /**
     * Checks param value if it is null (returns boolean).
     *
     * @param object value, needed to check.
     * @return true, if value is null and false, if it is not null.
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

}
