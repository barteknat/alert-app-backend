package com.alert.app.backend.repository;

import com.alert.app.backend.domain.WeatherStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherStationRepository extends JpaRepository<WeatherStation, Long> {

    WeatherStation getByCity(String city);
    boolean existsByTimeAndCity(long time, String city);
    WeatherStation getDistinctFirstByCityOrderByIdDesc(String city);
}
