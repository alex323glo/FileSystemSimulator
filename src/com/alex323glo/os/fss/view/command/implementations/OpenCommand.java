package com.alex323glo.os.fss.view.command.implementations;

import com.alex323glo.os.fss.controller.MenuController;
import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.model.descriptor.OpenedFileDescriptor;
import com.alex323glo.os.fss.view.command.Command;

/**
 * TODO add doc.
 */
// TODO finish implementation
public class OpenCommand<T, I> extends Command<T, I> {
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
     * @param params         parameters for command execution.
     * @param menuController instance of MenuController, needed to
     *                       carry ouy business logic operations.
     * @return true, if command execution was successful and false,
     * if it wasn't.
     * @see MenuController
     */
    @Override
    protected boolean executeLogic(String[] params, MenuController<T, I> menuController) {
        if (params[0] == null) {
            System.out.println("\tERROR: command param (name) is null");
            return false;
        }

        try {
            OpenedFileDescriptor<I, T> openedFileDescriptor = menuController.openFile(params[0]);
            System.out.println("\tFile \"" + params[0] + "\" was successfully opened.");
            System.out.println("\t" + openedFileDescriptor);
        } catch (FileSystemException e) {
//            e.printStackTrace();    // TODO could be replaced with logger
            System.out.println("\tERROR: " + e.getMessage());
        }

        return false;
    }
}
