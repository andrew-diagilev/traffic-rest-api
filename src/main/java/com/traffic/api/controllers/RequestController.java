package com.traffic.api.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import com.traffic.api.DAO.*;
import com.traffic.api.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private SpeedDAO speedDAO;
    @Autowired
    private AmountDAO amountDAO;
    @Autowired
    private DelayDAO delayDAO;
    @Autowired
    PublicTransportDAO publicTransportDAO;
    @Autowired
    TransitDAO transitDAO;
    @Autowired
    MainDashboard mainDashboard;
    @Autowired
    TrafficJamDAO trafficJamDAO;

    private String generateStartDate(String period) {
        Calendar cal = Calendar.getInstance();
        if (period.equals("last_half")) {
            cal.add(Calendar.DATE, -183);
        } else if (period.equals("last_month")) {
            cal.add(Calendar.DATE, -30);
        } else if (period.equals("last_week")) {
            cal.add(Calendar.DATE, -7);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(cal.getTime());
        return date;
    }


    /*    @RequestMapping(value = "/main_dash_board/by_time", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        public MainDashboard getData(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
            mainDashboard.setAmountList(amountDAO.getByTime(startTime.toString(), endTime.toString()));
            mainDashboard.setSpeedList(speedDAO.getByTime(startTime.toString(), endTime.toString()));
            mainDashboard.setDelayList(delayDAO.getByTime(startTime.toString(), endTime.toString()));
            return mainDashboard;
        }*/
    @RequestMapping(value = "/main_dash_board", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MainDashboard getData(@RequestParam("period") String period) {
        String startDate = generateStartDate(period);
        mainDashboard.setDelayList(delayDAO.getLast(startDate));
        mainDashboard.setAmountList(amountDAO.getLast(startDate));
        mainDashboard.setSpeedList(speedDAO.getLast(startDate));
        mainDashboard.setCurrentSpeed(speedDAO.getCurrent());
        mainDashboard.setCurrentDelay(delayDAO.getCurrent());
        mainDashboard.setCurrentAmount(amountDAO.getCurrent());
        mainDashboard.setTransitList(transitDAO.getLast(startDate));
        return mainDashboard;
    }


    @RequestMapping(value = "/speed/by_time", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Speed> getSpeedByTime(@RequestParam("start_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return speedDAO.getByTime(startTime.toString(), endTime.toString());
    }

    @RequestMapping(value = "/amount/by_time", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Amount> getAmountByTime(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return amountDAO.getByTime(startTime.toString(), endTime.toString());
    }

    @RequestMapping(value = "/delay/by_time", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Delay> getDelayByTime(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return delayDAO.getByTime(startTime.toString(), endTime.toString());
    }

    @RequestMapping(value = "/delay/last", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Delay> getDelayLast(@RequestParam("period") String period) {
        String date = generateStartDate(period);
        return delayDAO.getLast(date);
    }

    @RequestMapping(value = "/amount/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Amount> getAmountAll() {
        return amountDAO.findAll();
    }

    @RequestMapping(value = "/public_transport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<PublicTransport> getPublicTransportByTime(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return publicTransportDAO.getByTime(startTime.toString(), endTime.toString());

    }

    @RequestMapping(value = "/traffic_jam", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<TrafficJam> getTrafficJamsByTime(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return trafficJamDAO.getByTime(startTime.toString(), endTime.toString());
    }
}