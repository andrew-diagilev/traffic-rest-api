package com.traffic.api.services;


import com.traffic.api.models.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import java.util.*;

@Service
public class SnapshotService {

    @Autowired
    CameraConnectionService cameraConnectionService;
    @Autowired
    CameraService cameraService;

    public byte[] getSnapshot(Integer id) {
        try {
            String encodedBase64;
            String url = "/ISAPI/Streaming/channels/1/picture";
            Camera camera = cameraService.getCameraById(id);
            encodedBase64 = Base64.getEncoder().encodeToString((camera.loginPassToString()).getBytes("utf-8"));
            cameraConnectionService = new CameraConnectionService("http://" + camera.getIp()+":"+camera.getPort()+ url, new String(), "GET", encodedBase64);
            byte[] image = cameraConnectionService.doRequest();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
