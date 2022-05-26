package com.traffic.api.DAO;

import com.traffic.api.models.Delay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DelayDAO extends JpaRepository<Delay, Integer> {
    @Query(value ="SELECT vehicles_delay.id, vehicles_delay.time, vehicles_delay.current_delay, vehicles_history_delay.history_delay FROM db_traffic_lviv.vehicles_delay LEFT JOIN db_traffic_lviv.vehicles_history_delay ON time(vehicles_delay.time) = vehicles_history_delay.time and dayofweek(vehicles_delay.time) = vehicles_history_delay.day_of_week and year = '2020' where vehicles_delay.time >=? and vehicles_delay.time<=?  ORDER BY time", nativeQuery = true)
    public List<Delay> getByTime(String startTime, String endTime);

    @Query(value ="SELECT id, history_delay, avg(current_delay) as current_delay, date(time) as time FROM db_traffic_lviv.vehicles_delay where date(time) >= ?1 group by date(time) ORDER BY time", nativeQuery = true)
    public List<Delay> getLast(String startTime);

    @Query(value ="SELECT round(current_delay, 0) FROM db_traffic_lviv.vehicles_delay ORDER BY ID DESC LIMIT 1", nativeQuery = true)
    public Double getCurrent();
}

