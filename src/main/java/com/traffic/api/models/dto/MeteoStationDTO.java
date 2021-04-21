package com.traffic.api.models.dto;

import org.springframework.stereotype.Component;

@Component
public class MeteoStationDTO {
    private String id;
    private String location;
    private Double lat;
    private Double lon;

    public MeteoStationDTO() {
    }

    public MeteoStationDTO(String id, String location, Double lat, Double lon) {
        this.id = id;
        this.location = location;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
