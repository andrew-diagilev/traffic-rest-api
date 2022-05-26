package com.traffic.api.DAO;

import org.springframework.data.jpa.repository.Query;

public interface WeekDayDelayDAO {
    @Query(value ="SELECT DAYNAME(time), hour(time), avg(current_delay) FROM db_traffic_lviv.vehicles_delay group by hour(time), dayofweek(time);", nativeQuery = true)
    public Double getAvgDelay();

}
