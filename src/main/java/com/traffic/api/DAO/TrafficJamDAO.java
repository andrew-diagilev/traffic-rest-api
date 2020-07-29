package com.traffic.api.DAO;

import com.traffic.api.models.TrafficJam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrafficJamDAO extends JpaRepository<TrafficJam, Integer> {
/*    SELECT * FROM db_traffic.traffic_jams where date(capture_time) = '2020-07-20'  ORDER BY delay DESC LIMIT 20;*/
    @Query(value ="select * from traffic_jams where capture_time >= ?1 and capture_time <= ?2", nativeQuery = true)
    public List<TrafficJam> getByTime(String startTime, String endTime);
}
