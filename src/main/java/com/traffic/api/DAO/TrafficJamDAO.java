package com.traffic.api.DAO;

import com.traffic.api.models.TrafficJam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TrafficJamDAO extends JpaRepository<TrafficJam, Integer> {
    @Query(value = "select * from traffic_jams where capture_time >= ?1 and capture_time <= ?2", nativeQuery = true)
    public List<TrafficJam> getByTime(String startTime, String endTime);

    @Query(value ="SELECT * FROM db_traffic.traffic_jams where capture_time >= ?1  and capture_time <= ?2 GROUP by distance ORDER BY delay DESC LIMIT 20", nativeQuery = true)
    public List<TrafficJam> getTop(String startTime, String endTime);
}











