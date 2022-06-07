package com.traffic.api.models;

import com.traffic.api.DAO.PublicTransportAmountDAO;
import com.traffic.api.DAO.WeekDayDelayDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainDashboard {
    private List<Amount> amountList;
    private List<Speed> speedList;
    private List<Delay> delayList;
    private List<Transit> transitList;
    private List<WeekDayDelayDAO.WeekDayDelay> weekDayDelayList;
    private Integer currentSpeed;
    private Double currentDelay;
    private Integer currentAmount;
    private Integer currentTransit;
    private List<PublicTransportAmountDAO.PublicTransportAmount> currentPublicTransportAmountList;

    public List<Amount> getAmountList() {
        return amountList;
    }

    public void setAmountList(List<Amount> amountList) {
        this.amountList = amountList;
    }

    public List<Speed> getSpeedList() {
        return speedList;
    }

    public void setSpeedList(List<Speed> speedList) {
        this.speedList = speedList;
    }

    public List<Delay> getDelayList() {
        return delayList;
    }

    public void setDelayList(List<Delay> delayList) {
        this.delayList = delayList;
    }

    public List<Transit> getTransitList() {
        return transitList;
    }

    public void setTransitList(List<Transit> transitList) {
        this.transitList = transitList;
    }

    public List<WeekDayDelayDAO.WeekDayDelay> getWeekDayDelayList() {
        return weekDayDelayList;
    }

    public void setWeekDayDelayList(List<WeekDayDelayDAO.WeekDayDelay> weekDayDelayList) {
        this.weekDayDelayList = weekDayDelayList;
    }

    public Integer getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(Integer currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public Double getCurrentDelay() {
        return currentDelay;
    }

    public void setCurrentDelay(Double currentDelay) {
        this.currentDelay = currentDelay;
    }

    public Integer getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Integer currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Integer getCurrentTransit() {
        return currentTransit;
    }

    public void setCurrentTransit(Integer currentTransit) {
        this.currentTransit = currentTransit;
    }

    public List<PublicTransportAmountDAO.PublicTransportAmount> getCurrentPublicTransportAmountList() {
        return currentPublicTransportAmountList;
    }

    public void setCurrentPublicTransportAmountList(List<PublicTransportAmountDAO.PublicTransportAmount> currentPublicTransportAmountList) {
        this.currentPublicTransportAmountList = currentPublicTransportAmountList;
    }
}
