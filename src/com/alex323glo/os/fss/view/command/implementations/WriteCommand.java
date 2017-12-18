package com.alex323glo.os.fss.view.command.implementations;

import com.alex323glo.os.fss.controller.MenuController;
import com.alex323glo.os.fss.exception.FileSystemException;
import com.alex323glo.os.fss.model.file.FileBlock;
import com.alex323glo.os.fss.view.command.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TODO add doc.
 */
// TODO finish implementation
public class WriteCommand<T, I> extends Command<T, I> {
    /**
     * Gets required number of params for each Command implementation.
     *
     * @return required number of params. Must be not less then 0.
     */
    @Override
    protected int getNumOfParams() {
        return 3;
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
        if (params[0] == null || params[1] == null || params[2] == null) {
            System.out.println("\tERROR: command param (fd, offset or size) is null");
            return false;
        }
        Integer offset, size;

        try {
            offset = Integer.valueOf(params[1]);
            size = Integer.valueOf(params[2]);
        } catch (NumberFormatException e) {
            System.out.println("\tERROR: wrong number format of offset or/and size param.");
            return false;
        }

        System.out.println("\tEnter FileBlocks:");
        Scanner scanner = new Scanner(System.in);
        List<FileBlock> fileBlocks = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            System.out.print("\t" + (i + 1) + ") ");
            fileBlocks.add(new FileBlock(scanner.nextLine()));
        }
        System.out.println("\tWriting blocks...");

        try {
            menuController.writeFile(menuController.getOpenedFileDescriptorIdGenerator().generateOfString(params[0]),
                    fileBlocks, offset, size);

            System.out.println("\tData (" + fileBlocks.size() + " blocks) was successfully written to file \"" +
                    params[0] + "\" (from position " + offset + ").");

        } catch (FileSystemException e) {
            e.printStackTrace();
        }

        return false;
    }
}
