package com.traffic.api.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.traffic.api.DAO.*;
import com.traffic.api.models.*;
import com.traffic.api.services.AmountPlateTypeService;
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
    private PublicTransportDAO publicTransportDAO;
    @Autowired
    private TransitDAO transitDAO;
    @Autowired
    private MainDashboard mainDashboard;
    @Autowired
    private TrafficJamDAO trafficJamDAO;
    @Autowired
    private CrossingPointDAO crossingPointDAO;
    @Autowired
    private CrossingDAO crossingDAO;
    @Autowired
    private AmountPlateTypeService amountPlateTypeService;



    private String generateStartDate(String period) {
        Calendar cal = Calendar.getInstance();
        if (period.equals("last_half")) {
            /*Пол года*/
            cal.add(Calendar.DATE, -183);
        } else if (period.equals("last_month")) {
            /*Месяц*/
            cal.add(Calendar.DATE, -30);
        } else if (period.equals("last_week")) {
            /*Неделя*/
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
        mainDashboard.setTransitList(transitDAO.getLast(startDate));
        mainDashboard.setCurrentSpeed(speedDAO.getCurrent());
        mainDashboard.setCurrentDelay(delayDAO.getCurrent());
        mainDashboard.setCurrentAmount(amountDAO.getCurrent());

        return mainDashboard;
    }


    @RequestMapping(value = "/speed/by_time", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Speed> getSpeedByTime(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return speedDAO.getByTime(startTime.toString(), endTime.toString());
    }

    @RequestMapping(value = "/crossing_points/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CrossingPoint> getAllCrossingPoints() {
        return crossingPointDAO.getAll();
    }

    @RequestMapping(value = "/crossing/by_time", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Crossing> getCrossingByTime(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return crossingDAO.getByTime(startTime.toString(), endTime.toString());
    }

    @RequestMapping(value = "/amount/by_time", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Amount> getAmountByTime(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return amountDAO.getByTime(startTime.toString(), endTime.toString());
    }

    @RequestMapping(value = "/amount_plate_type", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AmountPlateTypeDAO.AmountView> getAmountPlateType() {
        return amountPlateTypeService.getAmountPlateType();
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

    @RequestMapping(value = "/traffic_jam/top", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<TrafficJam> getTrafficJamsTop(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return trafficJamDAO.getTop(startTime.toString(), endTime.toString());
    }

    @RequestMapping(value = "/transit/by_time", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Transit> getTransitByTime(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        return transitDAO.getByTime((localDate).toString());
    }
}