package com.dev.service;

import com.dev.exception.InvalidInputLineException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.dev.converter.FileToEntityConverter.convertFileToEntity;

public class InputOutputService {

    private final static Logger LOGGER = Logger.getLogger(WatchFolderService.class.getName());

    public static String IN_PATH = "/data/in";
    public static String FULL_IN_PATH = System.getProperty("user.dir") + IN_PATH;
    public static String OUT_PATH = "/data/out";
    public static String FULL_OUT_PATH = System.getProperty("user.dir") + OUT_PATH;

    public static void writeReportToFile(ReportDTO reportDTO, Path filename) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSS").format(new Date());
        String fullPath = FULL_OUT_PATH + "/" + timestamp + "-" + filename;
        File file = new File(fullPath);

        //Create the file
        try {
            if (file.createNewFile()) {
                LOGGER.log(Level.INFO, "Report file successfully created!");
                writeReportDTOToFile(reportDTO, fullPath);
            } else {
                LOGGER.log(Level.INFO, "File already exists");
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
            return Files.probeContentType(child).equals("text/plain");
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Error checking if file is of text type.");
            LOGGER.log(Level.SEVERE, e.toString());
            return false;
        }
    }

    public static FileDTO processNewFile(Path newPath) {
        try (Scanner scanner = new Scanner(new File(FULL_IN_PATH, newPath.toString()))) {
            var fileDTO = new FileDTO();
            int rowCounter = 0;

            while (scanner.hasNextLine()) {
                rowCounter++;
                try {
                    convertFileToEntity(scanner.nextLine(), fileDTO, rowCounter);
                } catch (InvalidInputLineException ex) {
                    LOGGER.log(Level.SEVERE, ex.toString());
                }
            }

            return fileDTO;
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.INFO, "Error reading file.");
            LOGGER.log(Level.SEVERE, e.toString());
        }

        return null;
    }
}
