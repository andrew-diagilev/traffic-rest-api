package com.traffic.api.controllers;


import com.traffic.api.DAO.MeteoParametersDAO;
import com.traffic.api.DAO.MeteoStationDAO;
import com.traffic.api.models.MeteoParameters;
import com.traffic.api.models.MeteoStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
@Controller
public class MeteoController {
    @Autowired
    private MeteoParametersDAO meteoParametersDAO;
    @Autowired
    private MeteoStationDAO meteoStationDAO;


    @RequestMapping(value = "/meteo_param/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MeteoParameters> getAllMeteoParam() {
        return meteoParametersDAO.findAll();
    }

    @RequestMapping(value = "/meteo_station/param/by_time", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MeteoStation> getParamByTime(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        List<MeteoStation> meteoStationList = meteoStationDAO.findAll();
        for(MeteoStation meteoStation: meteoStationList){
            meteoStation.setParametersList(meteoParametersDAO.findByNameAndCaptureTime(meteoStation.getId(), startTime.toString(), endTime.toString()));
        }

        return  meteoStationList;
    }

    @CrossOrigin
    @RequestMapping(value = "/meteo_station/param/last", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MeteoStation> getParamLast() {

        List<MeteoStation> meteoStationList = meteoStationDAO.findAll();
        for(MeteoStation meteoStation: meteoStationList){
            meteoStation.setParametersList(meteoParametersDAO.findTop1ByName(meteoStation.getId(), PageRequest.of(0,1)));
        }

        return  meteoStationList;
    }

    @CrossOrigin
    @RequestMapping(value = "/meteo_param/last_day", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MeteoParameters> getParamLastDayById(@RequestParam("id") String id) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.add(Calendar.DATE, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           List<MeteoParameters> meteoParametersList = meteoParametersDAO.findByNameAndCaptureTime(id, dateFormat.format(start.getTime()), dateFormat.format(end.getTime()));
        return  meteoParametersList;
    }

    @CrossOrigin
    @RequestMapping(value = "/meteo_station/param/last_day", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MeteoStation> getParamLastDay() {

        List<MeteoStation> meteoStationList = meteoStationDAO.findAll();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.add(Calendar.DATE, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(MeteoStation meteoStation: meteoStationList){
            meteoStation.setParametersList(meteoParametersDAO.findByNameAndCaptureTime(meteoStation.getId(), dateFormat.format(start.getTime()), dateFormat.format(end.getTime())));

        }
        return  meteoStationList;
    }





/*
    @RequestMapping(value = "/meteo_station/param/last", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MeteoStation> getLastParam() {
       return meteoStationDAO.findAllMeteo();
        meteoParametersDAO.findByMeteoStation()

        List<MeteoParameters> meteoStationList = meteoStationDAO.findLast(new PageRequest(0,1));
        return meteoStationList;

    }
*/



}
