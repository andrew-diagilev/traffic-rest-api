package com.traffic.api.DAO;

import com.traffic.api.models.MeteoParameters;
import com.traffic.api.models.MeteoStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface MeteoParametersDAO  extends JpaRepository<MeteoParameters, Integer>{
    @Query("select p from MeteoParameters p where p.meteoStation = :meteoStation")
    List<MeteoParameters> findByMeteoStation(@Param("meteoStation" )String meteoStation);


}
