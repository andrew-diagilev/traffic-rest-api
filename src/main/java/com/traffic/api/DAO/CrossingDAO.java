package com.traffic.api.DAO;


import com.traffic.api.models.Crossing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface CrossingDAO extends JpaRepository<Crossing, Integer> {
    @Query(value = "select ROW_NUMBER() OVER (ORDER BY amount) id, sum(vehicles_crossing.amount) as amount, crossing_point_id, crossing_point.road_name, crossing_point.lat, crossing_point.lon  FROM db_traffic_odessa.vehicles_crossing join db_traffic_odessa.crossing_point on crossing_point.id = vehicles_crossing.crossing_point_id where vehicles_crossing.start_time >= ?1  and vehicles_crossing.end_time <= ?2 and crossing_point.lat IS NOT NULL group by vehicles_crossing.crossing_point_id", nativeQuery = true)
    public List<Crossing> getByTime(String startTime, String endTime);

}
