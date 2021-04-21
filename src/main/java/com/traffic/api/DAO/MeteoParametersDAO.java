package com.traffic.api.DAO;

import com.traffic.api.models.MeteoParameters;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeteoParametersDAO  extends JpaRepository<MeteoParameters, Integer>{
/*    @Query("select p from MeteoParameters p where p.meteoStation = :meteoStation")
    List<MeteoParameters> findByMeteoStation(@Param("meteoStation" )String meteoStation);*/

    @Query("select p from MeteoParameters p where p.captureTime >= :startTime and p.captureTime <= :endTime and p.meteoStationName = :meteoStationName")
    List<MeteoParameters> findByNameAndCaptureTime(@Param("meteoStationName" )String name, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Query("select p from MeteoParameters p where p.meteoStationName = :meteoStationName order by p.captureTime desc")
    List<MeteoParameters> findTop1ByName (@Param("meteoStationName" )String name, Pageable pageable);



}
