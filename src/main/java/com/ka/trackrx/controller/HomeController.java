package com.ka.trackrx.controller;

import com.ka.trackrx.service.MedicationService;
import com.ka.trackrx.service.SymptomLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	private final MedicationService medicationService;
	private final SymptomLogService symptomLogService;

	public HomeController(MedicationService medicationService, SymptomLogService symptomLogService) {
		this.medicationService = medicationService;
		this.symptomLogService = symptomLogService;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("recentMedications", medicationService.findAll());
		model.addAttribute("recentSymptoms", symptomLogService.findAll());
		return "index";
	}
}
