package com.Lanja.finnancial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "records")
public class FinancialRecord {

    @Id
    @Column(name = "id_record")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecord;

    @Column(name = "record_name")
    private String recordName;

    @Column(name = "record_date")
    private Date recordDate;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_update_date")
    private Timestamp lastUpdateDate;

    @Column(name = "id_user")
    private Integer idUser;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "financialRecord")
    @JsonIgnore
    private List<RecordItem> recordItemList;

    public FinancialRecord() {
    }

    public FinancialRecord(Integer idRecord, String recordName, Date recordDate, Date createDate, Timestamp lastUpdateDate, Integer idUser, List<RecordItem> recordItemList) {
        this.idRecord = idRecord;
        this.recordName = recordName;
        this.recordDate = recordDate;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.idUser = idUser;
        this.recordItemList = recordItemList;
    }

    public Integer getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(Integer idRecord) {
        this.idRecord = idRecord;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setLastUpdateDate() {
        this.lastUpdateDate = new Timestamp(System.currentTimeMillis());
    }

    public void setCreateDate() {
        long millis=System.currentTimeMillis();
        this.createDate = new Date(millis);
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public List<RecordItem> getRecordItemList() {
        return recordItemList;
    }

    public void setRecordItemList(List<RecordItem> recordItemList) {
        this.recordItemList = recordItemList;
    }
}
