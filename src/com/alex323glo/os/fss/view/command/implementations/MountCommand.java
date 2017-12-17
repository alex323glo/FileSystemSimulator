package com.alex323glo.os.fss.view.command.implementations;

import com.alex323glo.os.fss.controller.MenuController;
import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.view.command.Command;

/**
 * Command implementation. Used to cover
 * "mount" menu item logic in ConsoleView.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see Command
 */
public class MountCommand<T, I> extends Command<T, I> {
    /**
     * Gets required number of params for each Command implementation.
     *
     * @return required number of params. Must be not less then 0.
     */
    @Override
    protected int getNumOfParams() {
        return 1;
    }

    /**
     * Executes command logic. Uses MenuController to carry out business logic.
     *
     * @param params parameters for command execution.
     * @param menuController instance of MenuController, needed to
     *                       carry ouy business logic operations.
     * @return true, if command execution was successful and false,
     * if it wasn't.
     *
     * @see MenuController
     */
    @Override
    public boolean executeLogic(String[] params, MenuController<T, I> menuController) {

        try {
            return menuController.mountFileSystem(params[0]) != null;
        } catch (FileSystemException e) {
            System.out.println("ERROR: can't mount FileSystem from file \"" + params[0] + "\"");
            e.printStackTrace();
        }

        return false;
    }
}
