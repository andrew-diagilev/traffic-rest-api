package com.traffic.api.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "meteo_param")
public class MeteoParameters {
    @Id
    private Integer id;
    private Double chemicalFactor;
    private Double WindDirectionAvg;
    private Double airPressure;
    private Double rainAccum;
    private Double rainIntens;
    private Double rainState;
    private Double windsSpeedAvq;
    private Double surfaceSignal;
    private Double windSpeedMax;
    private Double temperatureAir;
    private Double dewpointTemp;
    private Double freezingTemp;
    private Double pavementSurfaceTemp;
    private Double relativeHumidity;
    private Double waterHeight;
    private String meteoStationName;
    private String captureTime;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="meteoStationName", nullable=false, insertable=false, updatable=false)
    @JsonBackReference*/
    /*private MeteoStation meteoStation;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getChemicalFactor() {
        return chemicalFactor;
    }

    public void setChemicalFactor(Double chemicalFactor) {
        this.chemicalFactor = chemicalFactor;
    }

    public Double getWindDirectionAvg() {
        return WindDirectionAvg;
    }

    public void setWindDirectionAvg(Double windDirectionAvg) {
        WindDirectionAvg = windDirectionAvg;
    }

    public Double getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(Double airPressure) {
        this.airPressure = airPressure;
    }

    public Double getRainAccum() {
        return rainAccum;
    }

    public void setRainAccum(Double rainAccum) {
        this.rainAccum = rainAccum;
    }

    public Double getRainIntens() {
        return rainIntens;
    }

    public void setRainIntens(Double rainIntens) {
        this.rainIntens = rainIntens;
    }

    public Double getRainState() {
        return rainState;
    }

    public void setRainState(Double rainState) {
        this.rainState = rainState;
    }

    public Double getWindsSpeedAvq() {
        return windsSpeedAvq;
    }

    public void setWindsSpeedAvq(Double windsSpeedAvq) {
        this.windsSpeedAvq = windsSpeedAvq;
    }

    public Double getSurfaceSignal() {
        return surfaceSignal;
    }

    public void setSurfaceSignal(Double surfaceSignal) {
        this.surfaceSignal = surfaceSignal;
    }

    public Double getWindSpeedMax() {
        return windSpeedMax;
    }

    public void setWindSpeedMax(Double windSpeedMax) {
        this.windSpeedMax = windSpeedMax;
    }

    public Double getTemperatureAir() {
        return temperatureAir;
    }

    public void setTemperatureAir(Double temperatureAir) {
        this.temperatureAir = temperatureAir;
    }

    public Double getDewpointTemp() {
        return dewpointTemp;
    }

    public void setDewpointTemp(Double dewpointTemp) {
        this.dewpointTemp = dewpointTemp;
    }

    public Double getFreezingTemp() {
        return freezingTemp;
    }

    public void setFreezingTemp(Double freezingTemp) {
        this.freezingTemp = freezingTemp;
    }

    public Double getPavementSurfaceTemp() {
        return pavementSurfaceTemp;
    }

    public void setPavementSurfaceTemp(Double pavementSurfaceTemp) {
        this.pavementSurfaceTemp = pavementSurfaceTemp;
    }

    public Double getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(Double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public Double getWaterHeight() {
        return waterHeight;
    }

    public void setWaterHeight(Double waterHeight) {
        this.waterHeight = waterHeight;
    }

   public String getMeteoStationName() {
        return meteoStationName;
    }

    public void setMeteoStationName(String meteoStationName) {
        this.meteoStationName = meteoStationName;
    }

    public String getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }

/*    public MeteoStation getMeteoStation() {
        return meteoStation;
    }

    public void setMeteoStation(MeteoStation meteoStation) {
        this.meteoStation = meteoStation;
    }*/
}
