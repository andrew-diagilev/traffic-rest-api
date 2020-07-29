package com.traffic.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles_speed")
public class Speed {
    @Id
    private Integer id;
    private Integer vehiclesSpeed;
    private String startTime;
    private String endTime;

    public Speed(Integer id, Integer vehiclesSpeed, String startTime, String endTime) {
        this.id = id;
        this.vehiclesSpeed = vehiclesSpeed;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Speed() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVehiclesSpeed() {
        return vehiclesSpeed;
    }

    public void setVehiclesSpeed(Integer vehiclesSpeed) {
        this.vehiclesSpeed = vehiclesSpeed;
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