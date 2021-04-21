package com.traffic.api.controllers;


import com.traffic.api.models.Camera;
import com.traffic.api.services.CameraService;
import com.traffic.api.services.SnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.List;

@Controller
@EnableWebMvc
public class CameraController {
    @Autowired
    SnapshotService snapshotService;

    @Autowired
    CameraService cameraService;

    @CrossOrigin
    @RequestMapping(value = "/api/cameras/snapshot", method = RequestMethod.GET,  produces=MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody public byte[] getSnapshot(@RequestParam("id") Integer id) throws IOException {
        return snapshotService.getSnapshot(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/api/cameras/all", method = RequestMethod.GET,  produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public List<Camera> getAll() {
        return cameraService.getAllCameras();
    }



}
