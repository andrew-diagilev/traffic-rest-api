package com.traffic.api.models;


import javax.persistence.*;


@Entity
@Table(name = "vehicles_crossing")
public class Crossing {
    @Id
    private Integer id;
    private Integer amount;
    private String roadName;
    private Double lat;
    private Double lon;

    public Crossing() {
    }

    public Crossing(Integer id, Integer amount, String startTime, String endTime, String roadName, Double lat, Double lon) {
        this.id = id;
        this.amount = amount;
        this.roadName = roadName;
        this.lat = lat;
        this.lon = lon;
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


    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}