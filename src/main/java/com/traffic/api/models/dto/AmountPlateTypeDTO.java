package com.traffic.api.models.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "vehicles_amount_plate_type")
public class AmountPlateTypeDTO {
    @Id
    private Integer id;
    private Integer amount;
    private String startTime;
    private String endTime;
    @Transient
    private String region;

    public AmountPlateTypeDTO(Integer id, Integer amount, String startTime, String endTime, String region) {
        this.id = id;
        this.amount = amount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.region = region;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
