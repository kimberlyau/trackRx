package com.ka.trackrx.service;

import com.ka.trackrx.model.HealthLog;
import com.ka.trackrx.repository.HealthLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthLogService {
	private final HealthLogRepository healthLogRepository;

	public HealthLogService(HealthLogRepository healthLogRepository) {
		this.healthLogRepository = healthLogRepository;
	}

	public List<HealthLog> findAll() { return healthLogRepository.findAll(); }
	public HealthLog save(HealthLog log) { return healthLogRepository.save(log); }
	public void deleteById(Long id) { healthLogRepository.deleteById(id); }
}
