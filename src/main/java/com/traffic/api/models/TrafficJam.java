package com.traffic.api.models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "traffic_jams")
public class TrafficJam {
    @Id
    private Integer id;
    private String jamStartTime;
    private String jamType;
    private Double lat;
    private Double lon;
    private String jamStart;
    private String jamEnd;
    private Integer distance;
    private Integer delay;
    private String captureTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJamStartTime() {
        return jamStartTime;
    }

    public void setJamStartTime(String jamStartTime) {
        this.jamStartTime = jamStartTime;
    }

    public String getJamType() {
        return jamType;
    }

    public void setJamType(String jamType) {
        this.jamType = jamType;
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

    public String getJamStart() {
        return jamStart;
    }

    public void setJamStart(String jamStart) {
        this.jamStart = jamStart;
    }

    public String getJamEnd() {
        return jamEnd;
    }

    public void setJamEnd(String jamEnd) {
        this.jamEnd = jamEnd;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public String getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }
}
