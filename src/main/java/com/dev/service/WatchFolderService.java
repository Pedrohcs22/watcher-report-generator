package com.dev.service;

import com.dev.model.datatransferobject.FileDTO;
import com.dev.model.datatransferobject.ReportDTO;

import java.io.IOException;
import java.nio.file.*;

import static com.dev.service.CreateReportService.createReportFile;
import static com.dev.service.InputOutputService.processNewFile;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

public class WatchFolderService {

    public static void watchInputPathDirectory(Path path) {
        Files.isDirectory(path);

        System.out.println("Watching path: " + path);

        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {

            path.register(watcher, ENTRY_CREATE);

            while (true) {
                var key = watcher.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    var kind = event.kind();

                    // OVERFLOW events may happen even if your not subscribed to it
                    if (OVERFLOW == kind) {
                        continue; // loop
                    } else if (ENTRY_CREATE == kind) {

                        Path newPath = ((WatchEvent<Path>) event).context();

                        System.out.println("New path created: " + newPath);

                        // The filename is the
                        // context of the event.
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path filename = ev.context();

                        // Verify that the new
                        //  file is a text file.
                        InputOutputService.isTextFile(path, filename);

                        FileDTO fileDTO = processNewFile(newPath);

                        if (fileDTO != null) {
                            createReportFile(fileDTO);
                        }

                    }
                }

                if (!key.reset()) {
                    break; // loop
                }
            }

        } catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        }
    }
}
