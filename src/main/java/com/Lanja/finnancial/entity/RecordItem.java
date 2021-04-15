package com.Lanja.finnancial.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "record_items")
public class RecordItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_record_item")
    private Integer idRecordItem;

    @ManyToOne
    @JoinColumn(name = "id_record")
    private FinancialRecord financialRecord;

    @ManyToOne
    @JoinColumn(name = "id_sale")
    private Sale sale;

    @Column(name = "created_date")
    private Date createdDate;

    public RecordItem() {
    }

    public RecordItem(Integer idRecordItem, FinancialRecord financialRecord, Sale sale, Date createdDate) {
        this.idRecordItem = idRecordItem;
        this.financialRecord = financialRecord;
        this.sale = sale;
        this.createdDate = createdDate;
    }

    public Integer getIdRecordItem() {
        return idRecordItem;
    }

    public void setIdRecordItem(Integer idRecordItem) {
        this.idRecordItem = idRecordItem;
    }

    public FinancialRecord getFinancialRecord() {
        return financialRecord;
    }

    public void setFinancialRecord(FinancialRecord financialRecord) {
        this.financialRecord = financialRecord;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "RecordItem{" +
                "idRecordItem=" + idRecordItem +
                ", financialRecord=" + financialRecord +
                ", sale=" + sale +
                ", createdDate=" + createdDate +
                '}';
    }
}
