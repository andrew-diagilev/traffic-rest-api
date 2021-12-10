package com.traffic.api.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.traffic.api.models.MeteoParameters;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeteoStationDTO {
    private String id;
    private String location;
    private Double lat;
    private Double lon;
    private List<MeteoParameters> parametersList;

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
    public void setParametersList(List<MeteoParameters> parametersList) {
        this.parametersList = parametersList;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<MeteoParameters> getParametersList() {
        return parametersList;
    }
}
