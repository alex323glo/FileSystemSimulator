package com.alex323glo.os.fss;

import com.alex323glo.os.fss.controller.MenuController;
import com.alex323glo.os.fss.controller.MenuControllerImpl;
import com.alex323glo.os.fss.loader.VirtualFileSystemLoader;
import com.alex323glo.os.fss.model.descriptor.DescriptorID;
import com.alex323glo.os.fss.model.descriptor.FileDescriptor;
import com.alex323glo.os.fss.model.descriptor.StringDescriptorID;
import com.alex323glo.os.fss.model.descriptor.StringDescriptorIDGenerator;
import com.alex323glo.os.fss.model.system.FileSystem;
import com.alex323glo.os.fss.view.CommandMapper;
import com.alex323glo.os.fss.view.ConsoleView;
import com.alex323glo.os.fss.view.command.Command;
import com.alex323glo.os.fss.view.command.implementations.*;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO add doc
 */
public class Main {

    private static final int NUM_OF_DESCRIPTORS = 20;

    public static void main(String[] args) {
        ConsoleView<String, String> consoleView = new ConsoleView<>(
                new MenuControllerImpl<>(new VirtualFileSystemLoader<>(createFileSystemMap())),
                new CommandMapper<>(createCommandMap()));
        consoleView.run();
    }

    private static Map<String, FileSystem<String, String>> createFileSystemMap() {
        Map<String, FileSystem<String, String>> fileSystemMap = new HashMap<>();

        // filling Map with FileSystems:
        fileSystemMap.put("fs1", new FileSystem<>(
                createFileDescriptorMap("fs1", NUM_OF_DESCRIPTORS),
                new HashMap<>(),
                new StringDescriptorIDGenerator(),
                new StringDescriptorIDGenerator()
        ));

        return fileSystemMap;
    }

    private static Map<DescriptorID<String>, FileDescriptor<String>> createFileDescriptorMap(String modifier,
                                                                                             int numOfDescriptors) {
        if (modifier == null) {
            throw new NullPointerException("modifier is null");
        }

        if (numOfDescriptors < 1) {
            return null;
        }

        Map<DescriptorID<String>, FileDescriptor<String>> fileDescriptorMap = new HashMap<>();
        DescriptorID<String> tempDescriptorID;
        for (int i = 0; i < numOfDescriptors; i++) {
            tempDescriptorID = new StringDescriptorID(modifier + "_" + i);
            fileDescriptorMap.put(tempDescriptorID, new FileDescriptor<>(tempDescriptorID));
        }

        return fileDescriptorMap;
    }

    private static Map<String, Command<String, String>> createCommandMap() {
        Map<String, Command<String, String>> commandMap = new HashMap<>();

        // filling Map with Commands:
        commandMap.put("close", new CloseCommand<>());
        commandMap.put("create", new CreateCommand<>());
        commandMap.put("filestat", new FilestatCommand<>());
        commandMap.put("link", new LinkCommand<>());
        commandMap.put("ls", new LsCommand<>());
        commandMap.put("mount", new MountCommand<>());
        commandMap.put("open", new OpenCommand<>());
        commandMap.put("read", new ReadCommand<>());
        commandMap.put("truncate", new TruncateCommand<>());
        commandMap.put("unlink", new UnlinkCommand<>());
        commandMap.put("umount", new UnmountCommand<>());
        commandMap.put("write", new WriteCommand<>());

        return commandMap;
    }

}
