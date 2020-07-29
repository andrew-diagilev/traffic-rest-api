package com.traffic.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transit")
public class Transit {
    @Id
    private Integer id;
    private Integer amount;
    private String startTime;
    private String endTime;
    private String roadName;

    public Transit(Integer id, Integer amount, String startTime, String endTime, String roadName) {
        this.id = id;
        this.amount = amount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roadName = roadName;
    }

    public Transit() {
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

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }
}
