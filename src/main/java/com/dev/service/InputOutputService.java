package com.dev.service;

import com.dev.converter.FileToEntityConverter;
import com.dev.model.datatransferobject.FileDTO;
import com.dev.model.datatransferobject.ReportDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputOutputService {
    public static String IN_PATH = "/data/in";
    public static String FULL_IN_PATH = System.getProperty("user.dir") + IN_PATH;
    public static String OUT_PATH = "/data/out";
    public static String FULL_OUT_PATH = System.getProperty("user.dir") + OUT_PATH;

    public static void writeReportToFile(ReportDTO reportDTO, Path filename) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        String fullPath = FULL_OUT_PATH + "/" + timestamp + "-" + filename;
        File file = new File(fullPath);

        //Create the file
        try {
            if (file.createNewFile()) {
                System.out.println("File is created!");
                writeReportDTOToFile(reportDTO, fullPath);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeReportDTOToFile(ReportDTO reportDTO, String fullPath) throws IOException {
        Path path = Path.of(fullPath);
        writeStringToFile(path, "Worst Salesman: " + reportDTO.getNameOfWorstSalesman());
        writeStringToFile(path, "Amount of Clients: " + reportDTO.getAmountOfClients());
        writeStringToFile(path, "Amount of Salesman: " + reportDTO.getAmountOfSalesman());
        writeStringToFile(path, "Id of most expensive Sale: " + reportDTO.getIdOfMostExpensiveSale());
    }

    private static void writeStringToFile(Path path, String content) throws IOException {
        Files.writeString(path, content + System.lineSeparator(), StandardOpenOption.APPEND);
    }

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

    public static FileDTO processNewFile(Path newPath) {
        try (Scanner scanner = new Scanner(new File(FULL_IN_PATH, newPath.toString()))) {
            FileDTO fileDTO = new FileDTO();

            while (scanner.hasNextLine()) {
                FileToEntityConverter.convertFileToEntity(scanner, fileDTO);
            }

            return fileDTO;
        } catch (FileNotFoundException e) {
            System.err.format("IOException: %s%n", e);
        }

        return null;
    }
}
