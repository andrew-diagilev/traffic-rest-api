
package com.traffic.api.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.traffic.api.models.WeekDayDelayDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WeekDayDelayDAO extends JpaRepository<WeekDayDelayDTO, Integer> {
    @Query(value ="SELECT dayofweek(time) as dayOfWeek, DAYNAME(time) as dayName, hour(time) as hour, avg(current_delay) as delay FROM db_traffic_lviv.vehicles_delay group by hour(time), dayofweek(time) order by dayofweek;", nativeQuery = true)
    public List<WeekDayDelay> getAvgDelay();

    public static interface WeekDayDelay {
        @JsonIgnore
        Integer getId();
        Integer getDayOfWeek();
        String getDayName();
        Integer getHour();
        Double getDelay();
    }
}

