package com.ka.trackrx.controller;

import com.ka.trackrx.model.SymptomLog;
import com.ka.trackrx.service.SymptomLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/symptoms")
public class SymptomController {
	private final SymptomLogService symptomLogService;

	public SymptomController(SymptomLogService symptomLogService) {
		this.symptomLogService = symptomLogService;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("symptoms", symptomLogService.findAll());
		model.addAttribute("symptomLog", new SymptomLog());
		return "symptoms";
	}

	@PostMapping
	public String add(@ModelAttribute SymptomLog symptomLog) {
		// TODO integrate Flowise API call to analyze symptom and set aiInsight
		symptomLogService.save(symptomLog);
		return "redirect:/symptoms";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		symptomLogService.deleteById(id);
		return "redirect:/symptoms";
	}

	@GetMapping("/search")
	public String search(@RequestParam String query, Model model) {
		// TODO integrate Flowise API call to analyze symptom and set aiInsight
		// For now, search existing symptoms by name
		model.addAttribute("symptoms", symptomLogService.findBySymptomContaining(query));
		model.addAttribute("symptomLog", new SymptomLog());
		model.addAttribute("searchQuery", query);
		return "symptoms";
	}
}
