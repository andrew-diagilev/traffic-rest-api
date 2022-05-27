package com.traffic.api.controllers;

import com.traffic.api.DAO.AmountDAO;
import com.traffic.api.DAO.AmountPlateTypeDAO;
import com.traffic.api.models.Amount;
import com.traffic.api.services.AmountPlateTypeService;
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
public class AmountController {

    @Autowired
    private AmountDAO amountDAO;
    @Autowired
    private AmountPlateTypeService amountPlateTypeService;

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
}