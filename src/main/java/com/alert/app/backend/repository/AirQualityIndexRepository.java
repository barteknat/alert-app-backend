package com.alert.app.backend.repository;

import com.alert.app.backend.domain.AirQualityIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityIndexRepository extends JpaRepository<AirQualityIndex, Long> {

    AirQualityIndex getByStationApiId(long stationId);
    AirQualityIndex getDistinctFirstByStationApiIdOrderByIdDesc(long stationId);
//    AirQualityIndex getDistinctFirstByStationApiId(long stationId);
//    AirQualityIndex getByStationApiIdOrderByIdDesc(long stationId);
//    AirQualityIndex getByIdAndStationApiIdOrderByIdDesc(long id, long id2);
//    List<AirQualityIndex> getAllByStationApiIdOrderByDateDesc(long stationId);

}
