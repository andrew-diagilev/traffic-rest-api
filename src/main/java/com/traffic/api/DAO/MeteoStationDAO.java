package com.traffic.api.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeteoStationDAO extends JpaRepository<MeteoStation, String> {

/*    @Query("select distinct m from MeteoStation m join fetch m.parametersList p where p.captureTime >= :startTime and p.captureTime <= :endTime")
    List<MeteoStation> findByDateTime(@Param("startTime" )String startTime, @Param("endTime" )String endTime);*/

   @Query(value = "select distinct id, ip_address, port, lat, lon, location from meteo_station",  nativeQuery = true)
    List<MeteoStation> findAllMeteo();



/*   @Query("select distinct m from MeteoStation m inner join m.parametersList p")
    List<MeteoStation> findLast(Pageable pegeable);*/
}
