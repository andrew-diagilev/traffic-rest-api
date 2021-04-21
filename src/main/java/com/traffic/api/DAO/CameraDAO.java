package com.traffic.api.DAO;


import com.traffic.api.models.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CameraDAO extends JpaRepository<Camera, Integer> {
   public List<Camera> 	findAll();
   public Optional<Camera> findById(Integer id);
}
