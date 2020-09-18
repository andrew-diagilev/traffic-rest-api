package com.traffic.api.models;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "crossing_point")
public class CrossingPoint {
    @Id
    Integer id;
    String crossingName;
    String roadName;
    Double lat;
    Double lon;


    public CrossingPoint() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCrossingName() {
        return crossingName;
    }

    public void setCrossingName(String crossingName) {
        this.crossingName = crossingName;
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
