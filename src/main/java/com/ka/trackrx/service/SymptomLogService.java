package com.ka.trackrx.service;

import com.ka.trackrx.model.SymptomLog;
import com.ka.trackrx.repository.SymptomLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomLogService {
	private final SymptomLogRepository symptomLogRepository;

	public SymptomLogService(SymptomLogRepository symptomLogRepository) {
		this.symptomLogRepository = symptomLogRepository;
	}

	public List<SymptomLog> findAll() { return symptomLogRepository.findAll(); }
	public SymptomLog save(SymptomLog log) {
		// TODO integrate Flowise API call to analyze symptom and set aiInsight
		// TODO integrate Flowise API call here
		return symptomLogRepository.save(log);
	}
	public void deleteById(Long id) { symptomLogRepository.deleteById(id); }
	public List<SymptomLog> findBySymptomContaining(String query) {
		return symptomLogRepository.findBySymptomContainingIgnoreCase(query);
	}
}
