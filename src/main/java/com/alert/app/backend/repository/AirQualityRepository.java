package com.alert.app.backend.repository;

import com.alert.app.backend.domain.AirQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityRepository extends JpaRepository<AirQuality, Long> {

}
