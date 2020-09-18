package com.traffic.api.models;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meteo_station")
public class MeteoStation {
    @Id
        private String id;
        private String ipAddress;
        private int port;
        private double lat;
        private double lon;
        private String location;
       @OneToMany (mappedBy = "meteoStation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
       @JsonManagedReference
        private List<MeteoParameters> parametersList;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<MeteoParameters> getParametersList() {
        return parametersList;
    }

    public void setParametersList(List<MeteoParameters> parametersList) {
        this.parametersList = parametersList;
    }
}
