package com.alex323glo.os.fss.view.command.implementations;

import com.alex323glo.os.fss.controller.MenuController;
import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.model.file.AbstractFile;
import com.alex323glo.os.fss.model.file.File;
import com.alex323glo.os.fss.view.command.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO add doc.
 */
// TODO finish implementation
public class LsCommand<T, I> extends Command<T, I> {
    /**
     * Gets required number of params for each Command implementation.
     *
     * @return required number of params. Must be not less then 0.
     */
    @Override
    protected int getNumOfParams() {
        return 0;
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
        try {
            List<AbstractFile<T>> abstractFileList = menuController.getAllFiles();
            List<String> stringFileList = stringifyFileList(abstractFileList);

            System.out.println("\tFile list:");
            for (String row: stringFileList) {
                System.out.println(row);
            }

            return abstractFileList != null;
        } catch (FileSystemException e) {
            //            e.printStackTrace();    // TODO could be replaced with logger
            System.out.println("\tERROR: " + e.getMessage());
        }

        return false;
    }

    /**
     * TODO add doc.
     *
     * @param fileList
     * @return
     */
    private List<String> stringifyFileList(List<AbstractFile<T>> fileList) {
        if (fileList == null) {
            return null;
        }

        List<String> stringFileList = new ArrayList<>(fileList.size());
        for (int i = 0; i < fileList.size(); i++) {
            AbstractFile<T> tempFile = fileList.get(i);
            stringFileList.add(String.format("\t\t%d. \"%s\"  descriptor_id: \"%s\"",
                    i + 1, tempFile.getName(), tempFile.getDescriptor().getId().getID()));
        }

        return stringFileList;
    }
}
