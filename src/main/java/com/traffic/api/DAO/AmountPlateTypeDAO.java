package com.traffic.api.DAO;

import com.traffic.api.models.Amount;
import com.traffic.api.models.AmountPlateTypeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AmountPlateTypeDAO extends JpaRepository<AmountPlateTypeDTO, Integer> {

    @Query(value = "SELECT vehicles_amount_plate_type.id, amount, start_time as startTime, end_time as endTime, region FROM db_traffic_lviv.vehicles_amount_plate_type join db_traffic_lviv.license_plate_type on license_plate_type.id =  vehicles_amount_plate_type.license_plate_type where start_time >=?1 and start_time=end_time", nativeQuery = true)
    public List<AmountView> getByStartTime(String startTime);

    @Query(value = "SELECT vehicles_amount_plate_type.id, amount, start_time as startTime,  end_time as endTime, region FROM db_traffic_lviv.vehicles_amount_plate_type join db_traffic_lviv.license_plate_type on license_plate_type.id =  vehicles_amount_plate_type.license_plate_type where start_time = (select max(start_time) from db_traffic_lviv.vehicles_amount_plate_type)", nativeQuery = true)
    public List<AmountView> getLast();

    public interface AmountView {
        Integer getId();
        Integer getAmount();
        String getStartTime();
        String getEndTime();
        String getRegion();
    }
}
