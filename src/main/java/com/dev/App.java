package com.dev;

import java.nio.file.Path;

import static com.dev.service.InputOutputService.FULL_IN_PATH;
import static com.dev.service.WatchFolderService.watchInputPathDirectory;

public class App {

    public static void main(String[] args) {
        watchInputPathDirectory(Path.of(FULL_IN_PATH));
    }
}
