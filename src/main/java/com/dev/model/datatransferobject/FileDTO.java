package com.dev.model.datatransferobject;

import com.dev.model.Client;
import com.dev.model.Sale;
import com.dev.model.Salesman;

import java.util.ArrayList;
import java.util.List;

public class FileDTO {
    private List<Client> clients = new ArrayList<>();
    private List<Salesman> salesmen = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();

    public FileDTO() {
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Salesman> getSalesmen() {
        return salesmen;
    }

    public List<Sale> getSales() {
        return sales;
    }
}
