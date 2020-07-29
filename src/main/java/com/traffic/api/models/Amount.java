package com.traffic.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles_amount")
public class Amount {
    @Id
    private Integer id;
    private Integer amount;
    private String startTime;
    private String endTime;

    public Amount(Integer amount, String startTime, String endTime) {
        this.amount = amount;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Amount() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
