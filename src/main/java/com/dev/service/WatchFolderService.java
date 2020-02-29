package com.dev.service;

import com.dev.model.datatransferobject.FileDTO;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.dev.service.CreateReportService.createReportDTO;
import static com.dev.service.InputOutputService.*;
import static java.nio.file.Files.isDirectory;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

public class WatchFolderService {

    private final static Logger LOGGER = Logger.getLogger(WatchFolderService.class.getName());

    public static void watchInputPathDirectory(Path path) {
        if (!isDirectory(path)) {
            LOGGER.log(Level.SEVERE, "PATH: " + path + ", IS NOT A DIRECTORY");
            return;
        }

        LOGGER.log(Level.INFO, "Watching path: " + path);

        //ExecutorService executorService = Executors.newFixedThreadPool(4);
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {

            path.register(watcher, ENTRY_CREATE);

            while (true) {
                var key = watcher.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    var kind = event.kind();

                    // OVERFLOW events may happen even if you havent subscribed to it
                    if (OVERFLOW == kind) {
                        continue;
                    } else if (ENTRY_CREATE == kind) {

                        //executorService.submit();

                        // The filename is the
                        // context of the event.
                        Path filename = ((WatchEvent<Path>) event).context();

                        LOGGER.log(Level.INFO, "New file identified: " + filename);

                        // Verify that the new
                        //  file is a text file.
                        if (!isTextFile(path, filename)) {
                            LOGGER.log(Level.SEVERE, "File " + filename + "is not a text file, aborting operation.");
                            continue;
                        }

                        FileDTO fileDTO = processNewFile(filename);

                        if (fileDTO != null) {
                            var reportDTO = createReportDTO(fileDTO);
                            writeReportToFile(reportDTO, filename);
                        } else {
                            LOGGER.log(Level.WARNING, "No data retrieved from file: " + filename);
                        }
                    }
                }

                if (!key.reset()) {
                    break;
                }
            }

        } catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        } finally {
            //executorService.shutdown();
        }
    }
}
