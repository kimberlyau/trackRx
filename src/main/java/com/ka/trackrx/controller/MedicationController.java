package com.ka.trackrx.controller;

import com.ka.trackrx.model.Medication;
import com.ka.trackrx.service.MedicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medications")
public class MedicationController {
	private final MedicationService medicationService;

	public MedicationController(MedicationService medicationService) {
		this.medicationService = medicationService;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("medications", medicationService.findAll());
		model.addAttribute("medication", new Medication());
		return "medications";
	}

	@PostMapping
	public String add(@ModelAttribute Medication medication) {
		medicationService.save(medication);
		return "redirect:/medications";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		medicationService.deleteById(id);
		return "redirect:/medications";
	}
}
