package com.dev.service;

import com.dev.model.Client;
import com.dev.model.Item;
import com.dev.model.Sale;
import com.dev.model.Salesman;
import com.dev.model.datatransferobject.FileDTO;
import com.dev.model.datatransferobject.ReportDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests {@link CreateReportService}
 */
public class TestCreateReportService {

    @Test
    public void testCreateReportDTO() {
        FileDTO fileDTO = new FileDTO();
        fileDTO.getSales().addAll(getSales());
        fileDTO.getSalesmen().addAll(getSalesman());
        fileDTO.getClients().addAll(getClient());

        ReportDTO reportDTO = CreateReportService.createReportDTO(fileDTO);
        assertEquals(3, reportDTO.getAmountOfClients());
        assertEquals(2, reportDTO.getAmountOfSalesman());
        assertEquals(4, reportDTO.getIdOfMostExpensiveSale());
        assertEquals("Pedro", reportDTO.getNameOfWorstSalesman());
    }

    private List<Sale> getSales() {
        var sale = new Sale();
        sale.setSaleId(1);
        sale.setSalesmanName("Pedro");
        sale.getItems().add(new Item(1, 5, BigDecimal.TEN));

        var sale2 = new Sale();
        sale2.setSaleId(2);
        sale2.setSalesmanName("Maria");
        sale2.getItems().add(new Item(1, 20, BigDecimal.TEN));

        var sale3 = new Sale();
        sale3.setSaleId(3);
        sale3.setSalesmanName("Pedro");
        sale3.getItems().add(new Item(1, 5, BigDecimal.TEN));

        var sale4 = new Sale();
        sale4.setSaleId(4);
        sale4.setSalesmanName("Maria");
        sale4.getItems().add(new Item(1, 40, BigDecimal.TEN));

        return Arrays.asList(sale, sale2, sale3, sale4);
    }

    private List<Salesman> getSalesman() {
        var salesman = new Salesman();
        salesman.setName("Pedro");
        var salesman2 = new Salesman();
        salesman2.setName("Maria");

        return Arrays.asList(salesman, salesman2);
    }

    private List<Client> getClient() {
        var client = new Client();
        var client2 = new Client();
        var client3 = new Client();

        return Arrays.asList(client, client2, client3);
    }
}
