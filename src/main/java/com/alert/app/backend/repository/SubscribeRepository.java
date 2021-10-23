package com.alert.app.backend.repository;

import com.alert.app.backend.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    boolean existsByUserId(long id);
    boolean existsByUserIdAndAirQualityStation_City(long userId, String city);
    Subscribe getByUserIdAndAirQualityStation_City(long userId, String city);
}
