package com.traffic.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public_transport")
public class PublicTransport {
    @Id
    private Integer id;
    private String routeId;
    private String licensePlate;
    private String label;
    private Double currentSpeed;
    private Double lat;
    private Double lon;
    private String captureTime;

    public PublicTransport(Integer id, String routeId, String licensePlate, String label, Double currentSpeed, Double lat, Double lon, String captureTime) {
        this.id = id;
        this.routeId = routeId;
        this.licensePlate = licensePlate;
        this.label = label;
        this.currentSpeed = currentSpeed;
        this.lat = lat;
        this.lon = lon;
        this.captureTime = captureTime;
    }

    public PublicTransport() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(Double currentSpeed) {
        this.currentSpeed = currentSpeed;
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

    public String getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }
}
