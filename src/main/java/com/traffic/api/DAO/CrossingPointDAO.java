package com.traffic.api.DAO;

import com.traffic.api.models.CrossingPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface CrossingPointDAO extends JpaRepository<CrossingPoint, Integer> {
        @Query(value = "SELECT * FROM db_traffic_odessa.crossing_point", nativeQuery = true)
        public List<CrossingPoint> getAll();

    }
