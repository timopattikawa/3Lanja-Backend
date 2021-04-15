package com.Lanja.finnancial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private Integer idSale;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "id_market")
    private Market market;

    @Column(name = "price")
    private String price;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sale")
    @JsonIgnore
    private List<RecordItem> recordItemList;

    public Sale() { }

    public Sale(Integer idSale, Item item, Market market, String price, List<RecordItem> recordItemList) {
        this.idSale = idSale;
        this.item = item;
        this.market = market;
        this.price = price;
        this.recordItemList = recordItemList;
    }

    public Integer getIdSale() {
        return idSale;
    }

    public void setIdSale(Integer idSale) {
        this.idSale = idSale;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<RecordItem> getRecordItemList() {
        return recordItemList;
    }

    public void setRecordItemList(List<RecordItem> recordItemList) {
        this.recordItemList = recordItemList;
    }
}
