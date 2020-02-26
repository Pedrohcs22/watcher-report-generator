package com.dev.model;

import com.dev.model.enumeration.EntityIdentifier;

public class Sale {

    private static final EntityIdentifier entityIdentifier = EntityIdentifier.SALE;
    private long saleId;
    private long itemId;
    private int itemQuantity;
    private float itemPrice;
    private String salesmanName;

    public Sale() {
        super();
    }

    public Sale(long saleId, long itemId, int itemQuantity, float itemPrice, String salesmanName) {
        this.saleId = saleId;
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.salesmanName = salesmanName;
    }

    public long getSaleId() {
        return saleId;
    }

    public static EntityIdentifier getEntityIdentifier() {
        return entityIdentifier;
    }

    public void setSaleId(long saleId) {
        this.saleId = saleId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }
}
