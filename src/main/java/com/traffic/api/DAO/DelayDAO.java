package com.traffic.api.DAO;

import com.traffic.api.models.Delay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DelayDAO extends JpaRepository<Delay, Integer> {
    @Query(value ="SELECT vehicles_delay.id, vehicles_delay.time, vehicles_delay.current_delay, vehicles_history_delay.history_delay FROM db_traffic_odessa.vehicles_delay INNER JOIN db_traffic_odessa.vehicles_history_delay ON time(vehicles_delay.time) = vehicles_history_delay.time and dayofweek(vehicles_delay.time) = vehicles_history_delay.day_of_week where vehicles_delay.time >= ?1 and vehicles_delay.time<=?2 and year = '2020' ORDER BY time", nativeQuery = true)
    public List<Delay> getByTime(String startTime, String endTime);

    @Query(value ="SELECT id, history_delay, truncate(avg(current_delay), 2) as current_delay, date(time) as time FROM db_traffic_odessa.vehicles_delay where date(time) >= ?1 group by date(time) ORDER BY time", nativeQuery = true)
    public List<Delay> getLast(String startTime);

    @Query(value ="SELECT current_delay FROM db_traffic_odessa.vehicles_delay ORDER BY ID DESC LIMIT 1", nativeQuery = true)
    public Double getCurrent();

}

