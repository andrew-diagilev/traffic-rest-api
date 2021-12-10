package com.traffic.api.controllers;

import com.traffic.api.DAO.MeteoStationDAO;
import com.traffic.api.models.MeteoStation;
import com.traffic.api.models.dto.MeteoStationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MeteoStationController {

    @Autowired
    private MeteoStationDAO meteoStationDAO;

    @CrossOrigin
    @RequestMapping(value = "/api/meteo_station/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MeteoStationDTO> getAllMeteoStations() {
        List<MeteoStation> meteoStationList = meteoStationDAO.findAllMeteo();
        List<MeteoStationDTO> meteoStationDTOList = new ArrayList<>();
        for(MeteoStation meteoStation: meteoStationList){
            MeteoStationDTO meteoStationDTO = meteoStation.getDTO();
            meteoStationDTOList.add(meteoStationDTO);
        }
        return meteoStationDTOList;
    }

}
