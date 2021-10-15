package com.alert.app.backend.repository;

import com.alert.app.backend.domain.AirQualityStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityStationRepository extends JpaRepository<AirQualityStation, Long> {

    AirQualityStation getByStationApiId(long stationApiId);
    boolean existsByCity(String city);
    AirQualityStation getByCityLike(String city);
    boolean existsByCityLike(String city);
}
