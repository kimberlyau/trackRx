package com.ka.trackrx.repository;

import com.ka.trackrx.model.SymptomLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SymptomLogRepository extends JpaRepository<SymptomLog, Long> {
	List<SymptomLog> findBySymptomContainingIgnoreCase(String symptom);
}
