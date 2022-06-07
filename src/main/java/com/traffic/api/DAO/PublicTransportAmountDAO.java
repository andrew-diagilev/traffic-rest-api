package com.traffic.api.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.traffic.api.models.dto.PublicTransportAmountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublicTransportAmountDAO extends JpaRepository<PublicTransportAmountDTO, Integer> {

    @Query(value = "SELECT count(route_id) as amount, LEFT(route_short_name, (REGEXP_INSTR(route_short_name, '[0-9]')-1)) as type FROM db_traffic_lviv.public_transport JOIN public_transport_routes ON route_id=public_transport_routes.id where capture_time =(select max(capture_time) from db_traffic_lviv.public_transport) and current_speed>0 group by type", nativeQuery = true)
    public List<PublicTransportAmount> getAmountType();

    public static interface PublicTransportAmount{
        @JsonIgnore
        Integer getId();
        Integer getAmount();
        String getType();
    }

}
