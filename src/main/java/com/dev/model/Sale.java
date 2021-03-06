package com.dev.model;

import com.dev.model.enumeration.EntityIdentifier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sale extends AbstractEntity {

    private long saleId;
    private String salesmanName;
    private List<Item> items = new ArrayList<>();

    public Sale() {
        super(EntityIdentifier.SALE);
    }

    public Sale(long saleId, long itemId, String salesmanName) {
        super(EntityIdentifier.SALE);
        this.saleId = saleId;
        this.salesmanName = salesmanName;
    }

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public BigDecimal getSaleValue() {
        Optional<BigDecimal> reduce = getItems().stream()
                .map(item -> item.getItemPrice().multiply(BigDecimal.valueOf(item.getItemQuantity())))
                .reduce(BigDecimal::add);
        return reduce.orElse(BigDecimal.ZERO);
    }
}
