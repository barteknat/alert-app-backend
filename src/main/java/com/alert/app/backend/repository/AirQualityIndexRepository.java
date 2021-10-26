package com.alert.app.backend.repository;

import com.alert.app.backend.domain.AirQualityIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityIndexRepository extends JpaRepository<AirQualityIndex, Long> {

    AirQualityIndex getByStationApiId(long stationId);
    AirQualityIndex getDistinctFirstByStationApiIdOrderByIdDesc(long stationId);
}
