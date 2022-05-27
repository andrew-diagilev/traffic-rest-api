package com.traffic.api.controllers;

import com.traffic.api.DAO.DelayDAO;
import com.traffic.api.DAO.WeekDayDelayDAO;
import com.traffic.api.models.Delay;
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
public class DelayController {

    @Autowired
    private DelayDAO delayDAO;
    @Autowired
    private WeekDayDelayDAO weekDayDelayDAO;

    @RequestMapping(value = "/delay/by_time", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Delay> getDelayByTime(@RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return delayDAO.getByTime(startTime.toString(), endTime.toString());
    }

    @RequestMapping(value = "/delay/last", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Delay> getDelayLast(@RequestParam("period") String period) {
        String date = new DateRangeGenerator().generateStartDate(period);
        return delayDAO.getLast(date);
    }

    @RequestMapping(value = "/delay/by_weekday", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<WeekDayDelayDAO.WeekDayDelay> getDelayWeekDay() {
        return weekDayDelayDAO.getAvgDelay();
    }
}