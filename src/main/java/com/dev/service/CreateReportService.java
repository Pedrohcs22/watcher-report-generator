package com.dev.service;

import com.dev.model.Sale;
import com.dev.model.datatransferobject.FileDTO;
import com.dev.model.datatransferobject.ReportDTO;

import java.math.BigDecimal;
import java.util.*;

public class CreateReportService {

    public static ReportDTO createReportFile(FileDTO fileDTO) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setAmountOfClients(fileDTO.getClients().size());
        reportDTO.setAmountOfClients(fileDTO.getSalesmen().size());
        reportDTO.setIdOfMostExpensiveSale(idOfMostExpensiveSale(fileDTO.getSales()));
        reportDTO.setNameOfWorstSalesman(worstSalesmanName(fileDTO));
        return reportDTO;
    }

    public static long idOfMostExpensiveSale(List<Sale> sales) {
        Optional<Sale> max = sales.stream()
                .max(Comparator.comparing(Sale::getSaleValue));

        return max.map(Sale::getSaleId).orElse(0L);
    }

    public static String worstSalesmanName(FileDTO fileDTO) {
        Map<String, BigDecimal> salesBySalesmanName = new HashMap<>();

        fileDTO.getSalesmen()
                .forEach(salesman -> {
                    Optional<BigDecimal> allSalesSum = fileDTO.getSales().stream()
                            .filter(sale -> sale.getSalesmanName().equals(salesman.getName()))
                            .map(Sale::getSaleValue)
                            .reduce(BigDecimal::add);

                    salesBySalesmanName.put(salesman.getName(), allSalesSum.orElse(BigDecimal.ZERO));
                });


        return "";
    }

}
