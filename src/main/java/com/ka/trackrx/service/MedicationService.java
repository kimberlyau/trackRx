package com.ka.trackrx.service;

import com.ka.trackrx.model.Medication;
import com.ka.trackrx.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {
	private final MedicationRepository medicationRepository;

	public MedicationService(MedicationRepository medicationRepository) {
		this.medicationRepository = medicationRepository;
	}

	public List<Medication> findAll() { return medicationRepository.findAll(); }
	public Medication save(Medication medication) { return medicationRepository.save(medication); }
	public Optional<Medication> findById(Long id) { return medicationRepository.findById(id); }
	public void deleteById(Long id) { medicationRepository.deleteById(id); }
}
