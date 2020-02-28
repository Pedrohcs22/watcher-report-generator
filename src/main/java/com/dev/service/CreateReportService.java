package com.dev.service;

import com.dev.model.Sale;
import com.dev.model.datatransferobject.FileDTO;
import com.dev.model.datatransferobject.ReportDTO;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CreateReportService {

    public static ReportDTO createReportDTO(FileDTO fileDTO) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setAmountOfClients(fileDTO.getClients().size());
        reportDTO.setAmountOfSalesman(fileDTO.getSalesmen().size());
        reportDTO.setIdOfMostExpensiveSale(idOfMostExpensiveSale(fileDTO.getSales()));
        reportDTO.setNameOfWorstSalesman(worstSalesmanName(fileDTO));
        return reportDTO;
    }

    public static long idOfMostExpensiveSale(List<Sale> sales) {
        var maxOptional = sales.stream()
                .max(Comparator.comparing(Sale::getSaleValue));

        return maxOptional.map(Sale::getSaleId).orElse(0L);
    }

    public static String worstSalesmanName(FileDTO fileDTO) {
        var salesBySalesmanName = new HashMap<String, BigDecimal>();

        fileDTO.getSalesmen()
                .forEach(salesman -> {
                    Optional<BigDecimal> allSalesSum = fileDTO.getSales().stream()
                            .filter(sale -> sale.getSalesmanName().equals(salesman.getName()))
                            .map(Sale::getSaleValue)
                            .reduce(BigDecimal::add);

                    salesBySalesmanName.put(salesman.getName(), allSalesSum.orElse(BigDecimal.ZERO));
                });

        var minOptional = salesBySalesmanName.values().stream().min(Comparator.naturalOrder());

        return minOptional.map(bigDecimal -> salesBySalesmanName.entrySet().stream()
                .filter(e -> e.getValue().compareTo(bigDecimal) == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "))).orElse("");
    }

}
