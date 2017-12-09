package com.alex323glo.os.fss.view.command;

import com.alex323glo.os.fss.controller.MenuController;

/**
 * Console command interface. Its realisations must
 * cover menu items execution logic in ConsoleView.
 *
 * @param <T> type of FileDescriptor ID value in MenuController.
 * @param <I> type of OpenedFileDescriptor ID value in MenuController.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see com.alex323glo.os.fss.view.ConsoleView
 */
public interface Command<T, I> {

    /**
     * Executes logic of command.
     *
     * @param params parameters for command execution.
     * @param menuController instance of MenuController, needed to
     *                       carry ouy business logic operations.
     * @return true, if command execution was successful and false,
     * if it wasn't.
     *
     * @see MenuController
     */
    boolean execute(String[] params, MenuController<T, I> menuController);

}
