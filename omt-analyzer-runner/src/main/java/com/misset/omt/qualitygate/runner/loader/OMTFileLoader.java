package com.misset.omt.qualitygate.runner.loader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class OMTFileLoader {

    public static List<File> getOMTFiles() {
        File currentFolder = new File(System.getProperty("user.dir"));
        if (!currentFolder.exists()) {
            throw new RuntimeException("Could not find current folder: " + currentFolder);
        }
        return (List<File>) FileUtils.listFiles(currentFolder, new String[]{"omt"}, true);
    }

    public static String getWorkingDir() {
        return System.getProperty("user.dir");
    }

}
