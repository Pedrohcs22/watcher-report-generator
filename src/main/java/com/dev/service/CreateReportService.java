package com.dev.service;

import com.dev.model.Sale;
import com.dev.model.datatransferobject.FileDTO;
import com.dev.model.datatransferobject.ReportDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.joining;

public class CreateReportService {

    public static ReportDTO createReportDTO(FileDTO fileDTO) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setAmountOfClients(fileDTO.getClients().size());
        reportDTO.setAmountOfSalesman(fileDTO.getSalesmen().size());
        reportDTO.setIdOfMostExpensiveSale(idOfMostExpensiveSale(fileDTO.getSales()));
        reportDTO.setNameOfWorstSalesman(worstSalesmanName(fileDTO));
        return reportDTO;
    }

    private static long idOfMostExpensiveSale(List<Sale> sales) {
        var maxOptional = sales.stream()
                .max(comparing(Sale::getSaleValue));

        return maxOptional.map(Sale::getSaleId).orElse(0L);
    }

    private static String worstSalesmanName(FileDTO fileDTO) {
        var salesBySalesmanName = new HashMap<String, BigDecimal>();

        fileDTO.getSalesmen()
                .forEach(salesman -> {
                    Optional<BigDecimal> allSalesSum = fileDTO.getSales().stream()
                            .filter(sale -> sale.getSalesmanName().equals(salesman.getName()))
                            .map(Sale::getSaleValue)
                            .reduce(BigDecimal::add);

                    salesBySalesmanName.put(salesman.getName(), allSalesSum.orElse(BigDecimal.ZERO));
                });

        var minOptional = salesBySalesmanName.values().stream().min(naturalOrder());

        return minOptional.map(bigDecimal -> salesBySalesmanName.entrySet().stream()
                .filter(e -> e.getValue().compareTo(bigDecimal) == 0)
                .map(Map.Entry::getKey)
                .collect(joining(", "))).orElse("");
    }

}
