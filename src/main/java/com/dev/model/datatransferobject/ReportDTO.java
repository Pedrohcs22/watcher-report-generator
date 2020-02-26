package com.dev.model.datatransferobject;

import com.dev.model.Client;
import com.dev.model.Sale;
import com.dev.model.Salesman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportDTO {

    private List<Client> clients = new ArrayList<>();
    private List<Salesman> salesmen = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();

    public ReportDTO() {
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
