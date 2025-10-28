package com.ka.trackrx.repository;

import com.ka.trackrx.model.HealthLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthLogRepository extends JpaRepository<HealthLog, Long> {
}
