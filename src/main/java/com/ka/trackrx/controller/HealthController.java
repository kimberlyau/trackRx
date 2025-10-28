package com.ka.trackrx.controller;

import com.ka.trackrx.model.HealthLog;
import com.ka.trackrx.service.HealthLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/health")
public class HealthController {
	private final HealthLogService healthLogService;

	public HealthController(HealthLogService healthLogService) {
		this.healthLogService = healthLogService;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("healthLogs", healthLogService.findAll());
		model.addAttribute("healthLog", new HealthLog());
		return "health";
	}

	@PostMapping
	public String add(@ModelAttribute HealthLog healthLog) {
		healthLogService.save(healthLog);
		return "redirect:/health";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		healthLogService.deleteById(id);
		return "redirect:/health";
	}
}
