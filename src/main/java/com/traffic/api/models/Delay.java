package com.traffic.api.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles_delay")
public class Delay {
    @Id
    private Integer id;
    private String time;
    private Double currentDelay;
    private Double historyDelay;

    public Delay(String time, Double currentDelay, Double history_delay) {
        this.time = time;
        this.currentDelay = currentDelay;
        this.historyDelay = history_delay;
    }

    public Delay() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getCurrentDelay() {
        return currentDelay;
    }

    public void setCurrentDelay(Double currentDelay) {
        this.currentDelay = currentDelay;
    }

    public Double getHistory_delay() {
        return historyDelay;
    }

    public void setHistory_delay(Double history_delay) {
        this.historyDelay = history_delay;
    }
}
