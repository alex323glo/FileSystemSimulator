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
public abstract class Command<T, I> {

    /**
     * Executes menu command. Uses abstract method executeLogic() to define
     * main logic execution process and getNumOfParams() to get number of required
     * params for each Command implementation.
     *
     * @param params parameters for command execution.
     * @param menuController instance of MenuController, needed to
     *                       carry ouy business logic operations.
     * @return true, if command execution was successful and false,
     * if it wasn't.
     *
     * @see Command#getNumOfParams()
     * @see Command#executeLogic(String[], MenuController)
     * @see MenuController
     */
    public boolean execute(String[] params, MenuController<T, I> menuController) {

        if ((params == null && getNumOfParams() != 0) || menuController == null) {
            throw new NullPointerException("params or mainController is null");
        }

        if (params.length != getNumOfParams() || (getNumOfParams() != 0 && params[0] == null)) {
            System.out.println("\tERROR: wrong number of params (should be " + getNumOfParams() + ")");
            return false;
        }

        return executeLogic(params, menuController);
    }

    /**
     * Gets required number of params for each Command implementation.
     *
     * @return required number of params. Must be not less then 0.
     */
    protected abstract int getNumOfParams();

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
    protected abstract boolean executeLogic(String[] params, MenuController<T, I> menuController);

}
