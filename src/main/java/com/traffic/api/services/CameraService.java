package com.traffic.api.services;


import com.traffic.api.DAO.CameraDAO;
import com.traffic.api.models.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CameraService {
    @Autowired
    private CameraDAO cameraDAO;

    public List<Camera> getAllCameras() {
        return cameraDAO.findAll();
    }

    public Camera getCameraById(Integer id) {
        Camera camera = cameraDAO.findById(id).get();
        return camera;
    }
}
