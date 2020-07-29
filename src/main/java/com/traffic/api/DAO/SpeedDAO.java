package com.traffic.api.DAO;

import com.traffic.api.models.Speed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpeedDAO extends JpaRepository<Speed, Integer>{
    @Query(value ="select * from vehicles_speed where start_time >= ?1 and end_time <= ?2 order by start_time", nativeQuery = true)
    public List<Speed> getByTime(String startTime, String endTime);

    @Query(value ="SELECT id, truncate(avg(vehicles_speed), 1) as vehicles_speed,  date(start_time) as start_time, date(end_time) as end_time FROM db_traffic.vehicles_speed where date(start_time) >= ?1 group by date(start_time)", nativeQuery = true)
    public List<Speed> getLast(String startTime);

    @Query(value ="SELECT vehicles_speed  FROM vehicles_speed ORDER BY ID DESC LIMIT 1", nativeQuery = true)
    public Integer getCurrent();

}
