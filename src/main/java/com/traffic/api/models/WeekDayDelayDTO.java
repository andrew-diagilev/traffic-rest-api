package com.traffic.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WeekDayDelayDTO {
    @Id
    private Integer id;
    private Integer dayOfWeek;
    private String dayName;
    private Integer hour;
    private Double delay;

    public WeekDayDelayDTO(Integer id, Integer dayOfWeek, String dayName, Integer hour, Double delay) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.dayName = dayName;
        this.hour = hour;
        this.delay = delay;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Double getDelay() {
        return delay;
    }

    public void setDelay(Double delay) {
        this.delay = delay;
    }
}

