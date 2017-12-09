package com.alex323glo.os.fss.view.command;

import com.alex323glo.os.fss.controller.MenuController;

/**
 * Command implementation. Used to cover
 * "mount" menu item logic in ConsoleView.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see Command
 */
public class MountCommand<T, I> implements Command<T, I> {
    /**
     * Executes logic of command.
     *
     * @param params parameters for command execution.
     * @param menuController instance of MenuController, needed to
     *                       carry ouy business logic operations.
     * @return true, if command execution was successful and false,
     * if it wasn't.
     */
    @Override
    public boolean execute(String[] params, MenuController<T, I> menuController) {
        // TODO finish
        return false;
    }
}
