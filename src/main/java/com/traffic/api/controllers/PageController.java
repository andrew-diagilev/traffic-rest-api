package com.traffic.api.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String main() {
        return ("main");
    }

    @GetMapping("/public_transport")
    public String publicTransport() {
        return ("public_transport");
    }

    @GetMapping("/delay")
    public String delay() {
        return ("delay");
    }

    @GetMapping("/type")
    public String type() {
        return ("type");
    }

    @GetMapping("/amount")
    public String amount() {
        return ("amount");
    }

    @GetMapping("/incidents_list")
    public String incidentsList() {
        return ("incidents_list");
    }

    @GetMapping("/traffic_jam")
    public String traffic_jam() {
        return ("traffic_jam");
    }
}
