package com.traffic.api.controllers;


import com.traffic.api.DAO.MeteoParametersDAO;
import com.traffic.api.DAO.MeteoStationDAO;
import com.traffic.api.models.MeteoParameters;
import com.traffic.api.models.MeteoStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.List;
@Controller
public class MeteoController {
    @Autowired
    private MeteoParametersDAO meteoParametersDAO;
    @Autowired
    private MeteoStationDAO meteoStationDAO;
/*dfdfsd*/
    @RequestMapping(value = "/meteo_param/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MeteoParameters> getAllMeteoParam() {
        return meteoParametersDAO.findAll();
    }

    @RequestMapping(value = "/meteo_station/param/by_time", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MeteoStation> getParamByTime(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        List<MeteoStation> meteoStationList = meteoStationDAO.findByDateTime(startTime.toString(), endTime.toString());
        return meteoStationList;
    }

    @RequestMapping(value = "/meteo_station/param/last", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MeteoStation> getLastParam() {
       return meteoStationDAO.findAllMeteo();
/*        meteoParametersDAO.findByMeteoStation()

        List<MeteoParameters> meteoStationList = meteoStationDAO.findLast(new PageRequest(0,1));
        return meteoStationList;*/
    }



}
