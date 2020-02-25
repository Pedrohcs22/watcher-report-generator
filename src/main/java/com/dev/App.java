package com.dev;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.WatchEvent.Kind;

public class App {

    private static String IN_PATH = "/data/in";
    private static String FULL_IN_PATH = System.getProperty("user.dir") + IN_PATH;
    private static String OUT_PATH = "/data/out";

    public static void main(String[] args) {
        watchInputPathDirectory(Path.of(FULL_IN_PATH));
    }

    public static void watchInputPathDirectory(Path path) {
        // Sanity check - Check if path is a folder
        try {
            Boolean isFolder = (Boolean) Files.getAttribute(path,
                    "basic:isDirectory", NOFOLLOW_LINKS);
            if (!isFolder) {
                throw new IllegalArgumentException("Path: " + path
                        + " is not a folder");
            }
        } catch (IOException ioe) {
            // Folder does not exists
            ioe.printStackTrace();
        }

        System.out.println("Watching path: " + path);

        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {

            path.register(watcher, ENTRY_CREATE);

            // Start the infinite polling loop
            WatchKey key;
            while (true) {
                key = watcher.take();

                // Dequeueing events
                Kind<?> kind;

                for (WatchEvent<?> event : key.pollEvents()) {
                    // Get the type of the event
                    kind = event.kind();

                    if (OVERFLOW == kind) {
                        continue; // loop
                    } else if (ENTRY_CREATE == kind) {
                        // A new Path was created
                        Path newPath = ((WatchEvent<Path>) event)
                                .context();
                        // Output
                        System.out.println("New path created: " + newPath);

                        // The filename is the
                        // context of the event.
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path filename = ev.context();

                        // Verify that the new
                        //  file is a text file.
                        try {
                            // Resolve the filename against the directory.
                            // If the filename is "test" and the directory is "foo",
                            // the resolved name is "test/foo".
                            Path child = path.resolve(filename);
                            if (!Files.probeContentType(child).equals("text/plain")) {
                                System.err.format("New file '%s'" +
                                        " is not a plain text file.%n", filename);
                                continue;
                            }
                        } catch (IOException x) {
                            System.err.println(x);
                            continue;
                        }

                        Charset charset = StandardCharsets.UTF_8;
                        var pathOfFile = Path.of(FULL_IN_PATH + "\\" + newPath);
                        try (BufferedReader reader = Files.newBufferedReader(pathOfFile, charset)) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                        } catch (IOException x) {
                            System.err.format("IOException: %s%n", x);
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

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
