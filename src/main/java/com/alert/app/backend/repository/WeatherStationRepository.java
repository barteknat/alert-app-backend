package com.alert.app.backend.repository;

import com.alert.app.backend.domain.WeatherStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherStationRepository extends JpaRepository<WeatherStation, Long> {

    boolean existsByCity(String city);
    WeatherStation getByCityLike(String city);
    boolean existsByCityLike(String city);
    WeatherStation getByCity(String city);
}
