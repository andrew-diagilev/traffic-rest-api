package com.traffic.api.DAO;

import com.traffic.api.models.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransitDAO extends JpaRepository<Transit, Integer>{

    @Query(value ="select ROW_NUMBER() OVER (\n" +
            "\t\tORDER BY amount\n" +
            "\t) id, date(t.start_time) as start_time, date(t.start_time) as end_time, sum(t.amount) as amount, 'all' as road_name \n" +
            "    from (SELECT in_id, crossing_point.road_name, sum(amount) as amount, start_time \n" +
            "    FROM db_traffic_odessa.transit join crossing_point on crossing_point.id = transit.in_id \n" +
            "    where start_time >= ?1  \n" +
            "    group by in_id, start_time \n" +
            "    order by amount desc) as t \n" +
            "    group by start_time order by start_time", nativeQuery = true)
    public List<Transit> getLast(String startTime);

    @Query(value ="SELECT in_id as id, crossing_point.road_name, sum(amount) as amount, date(start_time) as start_time, date(end_time) as end_time FROM db_traffic.transit join crossing_point on crossing_point.id = transit.in_id  where date(start_time) = ?1  group by in_id order by amount desc", nativeQuery = true)
    public List<Transit> getByTime(String startTime);
}
