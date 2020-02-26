package com.dev.converter;

import com.dev.model.Client;
import com.dev.model.Sale;
import com.dev.model.Salesman;
import com.dev.model.datatransferobject.ReportDTO;
import com.dev.model.enumeration.EntityIdentifier;

import java.util.Optional;
import java.util.Scanner;

import static com.dev.model.enumeration.EntityIdentifier.*;

public class FileToEntityConverter {

    public static void convertFileToEntity(Scanner scanner, ReportDTO reportDTO) {
        String line = scanner.nextLine();
        var valuesArray = line.split("ç");

        int intIdentifier = Integer.parseInt(valuesArray[0]);

        Optional<EntityIdentifier> entityIdentifier = valueOf(intIdentifier);

        entityIdentifier.ifPresent(identifier -> {
                switch (identifier) {
                    case SALESMAN:
                        fileToSalesman(valuesArray, reportDTO);
                        break;
                    case CLIENT:
                        fileToClient(valuesArray, reportDTO);
                        break;
                    case SALE:
                        fileToSale(valuesArray, reportDTO);
                        break;
                    default:
                        break;
                }
        });
    }

    // Line example: 001çCPFçNameçSalary
    private static void fileToSalesman(String[] valuesArray, ReportDTO reportDTO) {
        Salesman salesman = new Salesman();
        salesman.setDocumentNumber(valuesArray[1]);
        salesman.setName(valuesArray[2]);
        salesman.setSalary(Float.parseFloat(valuesArray[3]));
        reportDTO.getSalesmen().add(salesman);
    }

    // Line example: 002çCNPJçNameçBusiness Area
    private static void fileToClient(String[] valuesArray, ReportDTO reportDTO) {
        Client client = new Client();
        client.setDocumentNumber(valuesArray[1]);
        client.setName(valuesArray[2]);
        client.setBusinessArea(valuesArray[3]);
        reportDTO.getClients().add(client);
    }

    // Line example: 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
    private static void fileToSale(String[] valuesArray, ReportDTO reportDTO) {
        Sale sale = new Sale();
        sale.setSaleId(Long.parseLong(valuesArray[1]));
        var itensData = valuesArray[2];
        sale.setSalesmanName(valuesArray[3]);
        reportDTO.getSales().add(sale);
    }
}
