package com.example.Scrapping.entity;

import jakarta.persistence.*;

@Entity
@Table
public class StockData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockId;

    private String name;
    private String price;
    private String gmpPremium;
    private String gainPer;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGainPer() {
        return gainPer;
    }

    public void setGainPer(String gainPer) {
        this.gainPer = gainPer;
    }

    public StockData(String gmpPremium) {
        this.gmpPremium = gmpPremium;
    }

    public String getGmpPremium() {
        return gmpPremium;
    }

    public void setGmpPremium(String gmpPremium) {
        this.gmpPremium = gmpPremium;
    }

    public StockData(){}
    public StockData(Long stockId, String name, String price) {
        this.stockId = stockId;
        this.name = name;
        this.price = price;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
