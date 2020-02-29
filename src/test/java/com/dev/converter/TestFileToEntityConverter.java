package com.dev.converter;

import com.dev.model.Item;
import com.dev.model.datatransferobject.FileDTO;
import com.dev.model.enumeration.EntityIdentifier;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests {@link FileToEntityConverter}
 */
public class TestFileToEntityConverter {
    @Test
    public void testFileToSalesman() {
        Scanner scanner = new Scanner("001ç1234567891234çPedroç50000");
        var fileDTO = new FileDTO();
        FileToEntityConverter.convertFileToEntity(scanner, fileDTO);
        var salesman = fileDTO.getSalesmen().get(0);
        assertEquals(salesman.getIdentifier(), EntityIdentifier.SALESMAN);
        assertEquals(salesman.getDocumentNumber(), "1234567891234");
        assertEquals(salesman.getName(), "Pedro");
        assertEquals(salesman.getSalary(), BigDecimal.valueOf(50000));
    }

    @Test
    public void testFileToClient() {
        Scanner scanner = new Scanner("002ç2345675434544345çJose da SilvaçRural");
        var fileDTO = new FileDTO();
        FileToEntityConverter.convertFileToEntity(scanner, fileDTO);
        var client = fileDTO.getClients().get(0);
        assertEquals(client.getIdentifier(), EntityIdentifier.CLIENT);
        assertEquals(client.getDocumentNumber(), "2345675434544345");
        assertEquals(client.getName(), "Jose da Silva");
        assertEquals(client.getBusinessArea(), "Rural");
    }

    @Test
    public void testFileToSale() {
        Scanner scanner = new Scanner("003ç10ç[1-10-100,2-30-2.50]çPedro");
        var fileDTO = new FileDTO();
        FileToEntityConverter.convertFileToEntity(scanner, fileDTO);
        var sale = fileDTO.getSales().get(0);
        assertEquals(EntityIdentifier.SALE, sale.getIdentifier());
        assertEquals(10, sale.getSaleId());
        assertEquals(2, sale.getItems().size());
        assertItems(sale.getItems());
        assertEquals("Pedro", sale.getSalesmanName());
    }

    private void assertItems(List<Item> itemList) {
        Item firstItem = itemList.get(0);
        assertEquals(1, firstItem.getItemId());
        assertEquals(0, firstItem.getItemPrice().compareTo(BigDecimal.valueOf(100)));
        assertEquals(10, firstItem.getItemQuantity());

        Item secondItem = itemList.get(1);
        assertEquals(2, secondItem.getItemId());
        assertEquals(0, secondItem.getItemPrice().compareTo(BigDecimal.valueOf(2.50)));
        assertEquals(30, secondItem.getItemQuantity());
    }
}
