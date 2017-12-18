package com.alex323glo.os.fss.view.command.implementations;

import com.alex323glo.os.fss.controller.MenuController;
import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.model.file.FileBlock;
import com.alex323glo.os.fss.view.command.Command;

import java.util.List;

/**
 * TODO add doc.
 */
// TODO finish implementation
public class TruncateCommand<T, I> extends Command<T, I> {
    /**
     * Gets required number of params for each Command implementation.
     *
     * @return required number of params. Must be not less then 0.
     */
    @Override
    protected int getNumOfParams() {
        return 2;
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
        if (params[0] == null || params[1] == null) {
            System.out.println("\tERROR: command param (name or size) is null");
            return false;
        }

        Integer newSize;
        try {
            newSize = Integer.valueOf(params[1]);
        } catch (NumberFormatException e) {
            System.out.println("\tERROR: wrong number format of size param.");
            return false;
        }

        try {
            List<FileBlock> fileBlockList = menuController.resizeFile(params[0], newSize);

            System.out.println("\tFile \"" + params[0] + "\" was successfuly resized (new size: " + newSize + ").");
            System.out.println("\tRemoved blocks:");
            if (fileBlockList != null) {
                for (FileBlock fileBlock: fileBlockList) {
                    System.out.println("\t\t" + fileBlock);
                }
            } else {
                System.out.println("\t\t<none>");
            }

            return fileBlockList != null;
        } catch (FileSystemException e) {
//            e.printStackTrace();    // TODO could be replaced with logger
            System.out.println("\tERROR: " + e.getMessage());
        }

        return false;
    }
}
