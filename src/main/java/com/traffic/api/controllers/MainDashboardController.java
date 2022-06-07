package com.traffic.api.controllers;

import com.traffic.api.DAO.*;
import com.traffic.api.models.MainDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainDashboardController {

    @Autowired
    private SpeedDAO speedDAO;
    @Autowired
    private AmountDAO amountDAO;
    @Autowired
    private DelayDAO delayDAO;
    @Autowired
    private WeekDayDelayDAO weekDayDelayDAO;
    @Autowired
    private TransitDAO transitDAO;
    @Autowired
    private PublicTransportAmountDAO publicTransportAmountDAO;
    @Autowired
    private MainDashboard mainDashboard;

    @RequestMapping(value = "/api/dashboard", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MainDashboard getData() {

        mainDashboard.setWeekDayDelayList(weekDayDelayDAO.getAvgDelay());
        mainDashboard.setCurrentSpeed(speedDAO.getCurrent());
        mainDashboard.setCurrentDelay(delayDAO.getCurrent());
        mainDashboard.setCurrentAmount(amountDAO.getCurrent());
        mainDashboard.setCurrentPublicTransportAmountList(publicTransportAmountDAO.getAmountType());
        return mainDashboard;
    }
}