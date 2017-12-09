package com.alex323glo.os.fss.view;

import com.alex323glo.os.fss.view.command.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * Console Command mapper class.
 *
 * @param <T> type of FileDescriptor ID value in MenuController.
 * @param <I> type of OpenedFileDescriptor ID value in MenuController.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see Command
 */
public class CommandMapper<T, I> {

    private Map<String, Command<T, I>> commandMap;

    /**
     * Constructor.
     */
    public CommandMapper() {
        commandMap = new HashMap<>();
    }

    /**
     * Initial constructor.
     *
     * @param commandMap initial field value.
     *
     * @see Command
     * @see Map
     */
    public CommandMapper(Map<String, Command<T, I>> commandMap) {
        if (commandMap == null) {
            throw new NullPointerException("commandMap is null");
        }

        this.commandMap = commandMap;
    }

    /**
     * CommandMap field getter.
     *
     * @return field value.
     *
     * @see Command
     * @see Map
     */
    public Map<String, Command<T, I>> getCommandMap() {
        return commandMap;
    }

    /**
     * CommandMap field setter.
     *
     * @param commandMap initial field value.
     *
     * @see Command
     * @see Map
     */
    public void setCommandMap(Map<String, Command<T, I>> commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Stores new Command by its name.
     *
     * @param name String name of new Command.
     * @param command new Command, needed to store.
     * @return Command, stored by inputed key, or null, if
     * CommandMapper doesn't contain Command with such name.
     *
     * @see Command
     */
    public Command<T, I> put(String name, Command<T, I> command) {
        if (name == null || command == null) {
            throw new NullPointerException("key or command is null");
        }

        return commandMap.put(name, command);
    }

    /**
     * Searches for Command by its name.
     *
     * @param name String name of searched Command.
     * @return needed Command, or null, if CommandMapper doesn't
     * store Command with such name.
     *
     * @see Command
     */
    public Command<T, I> get(String name) {
        if (name == null) {
            throw new NullPointerException("key is null");
        }

        return commandMap.get(name);
    }

}
