package com.alert.app.backend.repository;

import com.alert.app.backend.domain.AirQualitySensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirQualitySensorRepository extends JpaRepository<AirQualitySensor, Long> {

    List<AirQualitySensor> getByStationApiId(long stationId);
    AirQualitySensor findByStationApiId(long stationId);
}
