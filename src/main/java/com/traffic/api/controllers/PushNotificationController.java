package com.traffic.api.controllers;

import com.traffic.api.services.PushNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;


@Controller
public class PushNotificationController {

    @Autowired
    PushNotificationService pushNotificationService;

    @CrossOrigin
    @RequestMapping(value = "/api/push_notification/token/{token}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public String subscribeTokenToTopic(@PathVariable("token") String token)  throws ServletException {
        return pushNotificationService.subscribeTokenToTopic(token);
    }

}
