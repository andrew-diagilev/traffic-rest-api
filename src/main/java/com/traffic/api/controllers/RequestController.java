package com.traffic.api.controllers;

import com.traffic.api.DAO.AmountDAO;
import com.traffic.api.DAO.DelayDAO;
import com.traffic.api.DAO.SpeedDAO;
import com.traffic.api.DAO.TransitDAO;
import com.traffic.api.models.MainDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestController {

    @Autowired
    private SpeedDAO speedDAO;
    @Autowired
    private AmountDAO amountDAO;
    @Autowired
    private DelayDAO delayDAO;
    @Autowired
    private TransitDAO transitDAO;
    @Autowired
    private MainDashboard mainDashboard;

    @RequestMapping(value = "/main_dash_board", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MainDashboard getData(@RequestParam("period") String period) {
        String startDate = new DateRangeGenerator().generateStartDate(period);
        mainDashboard.setDelayList(delayDAO.getLast(startDate));
        mainDashboard.setAmountList(amountDAO.getLast(startDate));
        mainDashboard.setSpeedList(speedDAO.getLast(startDate));
        mainDashboard.setTransitList(transitDAO.getLast(startDate));
        mainDashboard.setCurrentSpeed(speedDAO.getCurrent());
        mainDashboard.setCurrentDelay(delayDAO.getCurrent());
        mainDashboard.setCurrentAmount(amountDAO.getCurrent());
        return mainDashboard;
    }
}