package com.Lanja.finnancial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Integer idItem;

    @Column(name = "item_name")
    private String itemName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    @JsonIgnore
    private List<Sale> sale;

    public Item() {
    }

    public Item(Integer idItem, String itemName, List<Sale> sale) {
        this.idItem = idItem;
        this.itemName = itemName;
        this.sale = sale;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<Sale> getSale() {
        return sale;
    }

    public void setSale(List<Sale> sale) {
        this.sale = sale;
    }
}
