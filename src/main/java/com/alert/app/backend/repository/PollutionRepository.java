package com.alert.app.backend.repository;

import com.alert.app.backend.domain.Pollution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollutionRepository extends JpaRepository<Pollution, Long> {
}
