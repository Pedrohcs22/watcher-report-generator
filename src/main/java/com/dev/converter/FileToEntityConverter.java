package com.dev.converter;

import com.dev.model.Client;
import com.dev.model.Item;
import com.dev.model.Sale;
import com.dev.model.Salesman;
import com.dev.model.datatransferobject.FileDTO;
import com.dev.model.enumeration.EntityIdentifier;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import static com.dev.model.enumeration.EntityIdentifier.*;

public class FileToEntityConverter {

    public static void convertFileToEntity(Scanner scanner, FileDTO fileDTO) {
        String line = scanner.nextLine();
        var valuesArray = line.split("ç");

        int intIdentifier = Integer.parseInt(valuesArray[0]);

        Optional<EntityIdentifier> entityIdentifier = valueOf(intIdentifier);

        entityIdentifier.ifPresent(identifier -> {
                switch (identifier) {
                    case SALESMAN:
                        fileToSalesman(valuesArray, fileDTO);
                        break;
                    case CLIENT:
                        fileToClient(valuesArray, fileDTO);
                        break;
                    case SALE:
                        fileToSale(valuesArray, fileDTO);
                        break;
                    default:
                        break;
                }
        });
    }

    // Line example: 001çCPFçNameçSalary
    private static void fileToSalesman(String[] valuesArray, FileDTO fileDTO) {
        Salesman salesman = new Salesman();
        salesman.setDocumentNumber(valuesArray[1]);
        salesman.setName(valuesArray[2]);
        salesman.setSalary(new BigDecimal(valuesArray[3]));
        fileDTO.getSalesmen().add(salesman);
    }

    // Line example: 002çCNPJçNameçBusiness Area
    private static void fileToClient(String[] valuesArray, FileDTO fileDTO) {
        Client client = new Client();
        client.setDocumentNumber(valuesArray[1]);
        client.setName(valuesArray[2]);
        client.setBusinessArea(valuesArray[3]);
        fileDTO.getClients().add(client);
    }

    // Line example: 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name
    private static void fileToSale(String[] valuesArray, FileDTO fileDTO) {
        Sale sale = new Sale();
        sale.setSaleId(Long.parseLong(valuesArray[1]));
        fillItensData(valuesArray[2], sale);
        sale.setSalesmanName(valuesArray[3]);
        fileDTO.getSales().add(sale);
    }

    private static void fillItensData(String itensData, Sale sale) {
        String itemsWithoutBrackets = itensData.replace("[", "").replace("]", "");
        String[] items = itemsWithoutBrackets.split(",");
        Arrays.stream(items).forEach(item -> {
            fileToItem(sale, item);
        });
    }

    private static void fileToItem(Sale sale, String item) {
        var itemData = item.split("-");
        Item newItem = new Item();
        newItem.setItemId(Long.parseLong(itemData[0]));
        newItem.setItemQuantity(Integer.parseInt(itemData[1]));
        newItem.setItemPrice(new BigDecimal(itemData[2]));
        sale.getItems().add(newItem);
    }
}
