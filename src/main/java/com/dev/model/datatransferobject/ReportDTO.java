package com.dev.model.datatransferobject;

public class ReportDTO {
    private int amountOfClients;
    private int amountOfSalesman;
    private long idOfMostExpensiveSale;
    private String nameOfWorstSalesman;

    public ReportDTO() {
    }

    public int getAmountOfClients() {
        return amountOfClients;
    }

    public void setAmountOfClients(int amountOfClients) {
        this.amountOfClients = amountOfClients;
    }

    public int getAmountOfSalesman() {
        return amountOfSalesman;
    }

    public void setAmountOfSalesman(int amountOfSalesman) {
        this.amountOfSalesman = amountOfSalesman;
    }

    public long getIdOfMostExpensiveSale() {
        return idOfMostExpensiveSale;
    }

    public void setIdOfMostExpensiveSale(long idOfMostExpensiveSale) {
        this.idOfMostExpensiveSale = idOfMostExpensiveSale;
    }

    public String getNameOfWorstSalesman() {
        return nameOfWorstSalesman;
    }

    public void setNameOfWorstSalesman(String nameOfWorstSalesman) {
        this.nameOfWorstSalesman = nameOfWorstSalesman;
    }
}
