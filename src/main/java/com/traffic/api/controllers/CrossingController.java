package com.traffic.api.controllers;

import com.traffic.api.DAO.CrossingDAO;
import com.traffic.api.DAO.CrossingPointDAO;
import com.traffic.api.models.Crossing;
import com.traffic.api.models.CrossingPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CrossingController {

    @Autowired
    private CrossingPointDAO crossingPointDAO;
    @Autowired
    private CrossingDAO crossingDAO;

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
}