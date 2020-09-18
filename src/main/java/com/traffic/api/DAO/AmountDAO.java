package com.traffic.api.DAO;


import com.traffic.api.models.Amount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmountDAO extends JpaRepository<Amount, Integer> {
    @Query(value = "select * from vehicles_amount where start_time >= ?1 and end_time <= ?2 and start_time <> end_time ORDER BY start_time", nativeQuery = true)
    public List<Amount> getByTime(String startTime, String endTime);

    @Query(value = "SELECT id, convert(start_time, DATE) as start_time, convert(end_time, DATE) as end_time, amount FROM db_traffic.vehicles_amount where start_time >=?1 and start_time=end_time", nativeQuery = true)
    public List<Amount> getLast(String startTime);

    @Query(value = "SELECT amount FROM vehicles_amount where start_time=end_time ORDER BY start_time DESC LIMIT 1", nativeQuery = true)
    public Integer getCurrent();
}




