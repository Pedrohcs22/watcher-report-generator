package com.dev.service;

import com.dev.converter.FileToEntityConverter;
import com.dev.model.datatransferobject.ReportDTO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputOutputService {
    public static String IN_PATH = "/data/in";
    public static String FULL_IN_PATH = System.getProperty("user.dir") + IN_PATH;
    public static String OUT_PATH = "/data/out";

    public static boolean isTextFile(Path path, Path filename) {
        try {
            Path child = path.resolve(filename);
            if (!Files.probeContentType(child).equals("text/plain")) {
                System.err.format("New file '%s'" +
                        " is not a plain text file.%n", filename);
                return false;
            }

            return true;
        } catch (IOException x) {
            System.err.println(x);
            return false;
        }
    }

    public static void processNewFile(Path newPath) {
        try (Scanner scanner = new Scanner(new File(FULL_IN_PATH, newPath.toString()))) {

            ReportDTO reportDTO = new ReportDTO();

            while (scanner.hasNextLine()) {
                FileToEntityConverter.convertFileToEntity(scanner, reportDTO);
            }

            reportDTO.getSales();
        } catch (FileNotFoundException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
