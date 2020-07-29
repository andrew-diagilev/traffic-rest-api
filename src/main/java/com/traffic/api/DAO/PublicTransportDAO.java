package com.traffic.api.DAO;


import com.traffic.api.models.Amount;
import com.traffic.api.models.PublicTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PublicTransportDAO extends JpaRepository<PublicTransport, Integer> {
        @Query(value ="select * from public_transport where capture_time >= ?1 and capture_time <= ?2 and current_speed <> '0'", nativeQuery = true)
        public List<PublicTransport> getByTime(String startTime, String endTime);
    }

