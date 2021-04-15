package com.Lanja.finnancial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "markets")
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_market")
    private Integer idMarket;

    @Column(name = "market_name")
    private String marketName;

    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "market")
    @JsonIgnore
    private List<Sale> sales;

    public Market() {
    }

    public Market(Integer idMarket, String marketName, String address, List<Sale> sales) {
        this.idMarket = idMarket;
        this.marketName = marketName;
        this.address = address;
        this.sales = sales;
    }

    public Integer getIdMarket() {
        return idMarket;
    }

    public void setIdMarket(Integer idMarket) {
        this.idMarket = idMarket;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
