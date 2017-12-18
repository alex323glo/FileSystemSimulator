package com.alex323glo.os.fss.view;

import com.alex323glo.os.fss.controller.MenuController;
import com.alex323glo.os.fss.holder.ObjectHolder;
import com.alex323glo.os.fss.view.command.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * View part of console application. Uses MenuController to work
 * with business logic.
 *
 * @param <T> type of FileDescriptor ID value in MenuController.
 * @param <I> type of OpenedFileDescriptor ID value in MenuController.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see MenuController
 */
public class ConsoleView<T, I> {

    private MenuController<T, I> menuController;
    private CommandMapper<T, I> commandMapper;
    private ConsoleListener consoleListener = new ConsoleListener(System.in);

    /**
     * Initial constructor.
     *
     * @param menuController initial field value.
     * @param commandMapper initial field value.
     *
     * @see MenuController
     * @see T
     * @see I
     * */
    public ConsoleView(MenuController<T, I> menuController, CommandMapper<T, I> commandMapper) {
        if (menuController == null || commandMapper == null) {
            throw new NullPointerException("menuController or commandMapper is null");
        }

        this.menuController = menuController;
        this.commandMapper = commandMapper;
    }

    /**
     * Starts console app execution.
     * */
    public void run() {
        chooseMenuItem();
    }

    /**
     * Translates user input to console into Command logic execution.
     * Uses manageMenuItems() method to manage Commands.
     *
     * @see ConsoleView#manageMenuItems(String)
     */
    private void chooseMenuItem() {

        while (true) {
            if (true) {
                manageMenuItems(consoleListener.getInput());
            }
        }

    }

    /**
     * Manage menu items' logic execution. Uses CommandMapper and Command
     * to switch execution commands by their names.
     *
     * @param consoleInput String user console input result.
     * @return result of switched command execution.
     *
     * @see Command
     * @see CommandMapper
     */
    private boolean manageMenuItems(String consoleInput) {

        if (consoleInput == null) {
            throw new NullPointerException("consoleInput is null");
        }

        if (consoleInput.length() < 1 || consoleInput.trim().length() < 1) {
            System.out.println("\tERROR: Wrong command (empty command line).");
            return false;
        }

        // If user choose to exit app:
        if (consoleInput.trim().toLowerCase().equals("exit")) {
            System.exit(0);
        }

        // Parses input String:
        List<String> partsList = Arrays.asList(consoleInput.split(" "));

        List<String> newPartsList = new ArrayList<>();
        for (String part: partsList) {
            if (part.length() > 0) {
                newPartsList.add(part);
            }
        }

        String[] parts = new String[newPartsList.size()];
        parts = newPartsList.toArray(parts);

        if (parts.length < 1) {
            System.out.println("\tERROR: Wrong command (empty command line).");
            return false;
        }

        String commandName = parts[0].toLowerCase();
        String[] params = Arrays.copyOfRange(parts, 1, parts.length);

        Command<T, I> command = commandMapper.get(commandName);
        if (command == null) {
            System.out.println("\tERROR: Command is null (wasn't parsed correctly).");
            return false;
        }

        return command.execute(params, menuController);
    }



}
