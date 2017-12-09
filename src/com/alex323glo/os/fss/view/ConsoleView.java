package com.alex323glo.os.fss.view;

import com.alex323glo.os.fss.controller.MenuController;
import com.alex323glo.os.fss.holder.ObjectHolder;
import com.alex323glo.os.fss.view.command.Command;

import java.util.Arrays;

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
     * @see MenuController
     * @see T
     * @see I
     * */
    public ConsoleView(CommandMapper<T, I> commandMapper) {
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
            if (!manageMenuItems(consoleListener.getInput())) {
                break;
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

        String[] parts = consoleInput.split(" ");
        if (parts.length < 2) {
            System.out.println("ERROR: Wrong command.");
            return false;
        }

        Arrays.stream(parts).map(String::trim).count();

        String commandName = parts[0].toLowerCase();
        String[] params = Arrays.copyOfRange(parts, 1, parts.length - 1);

        Command<T, I> command = commandMapper.get(commandName);
        if (command == null) {
            System.out.println("ERROR: Wrong command.");
            return false;
        }

        return command.execute(params, menuController);
    }



}
