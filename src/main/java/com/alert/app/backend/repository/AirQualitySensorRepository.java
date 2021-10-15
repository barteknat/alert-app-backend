package com.alert.app.backend.repository;

import com.alert.app.backend.domain.AirQualitySensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualitySensorRepository extends JpaRepository<AirQualitySensor, Long> {

}
