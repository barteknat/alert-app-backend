package com.alert.app.backend.repository;

import com.alert.app.backend.domain.PollutionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollutionValueRepository extends JpaRepository<PollutionValue, Long> {

}
