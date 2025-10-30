package com.ka.trackrx.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ka.trackrx.model.SymptomLog;
import com.ka.trackrx.service.SymptomLogService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/symptoms")
public class SymptomController {
	private final SymptomLogService symptomLogService;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String FLOWISE_URL = "http://localhost:3000/api/v1/prediction/80ae170c-f492-450f-9410-632ef652464b";

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

	@GetMapping("/ai")
	public String ai(@RequestParam String query, Model model) {
        try {
            String jsonPayload = "{\"question\": \"" + query + "\"}";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

            // Call Flowise
            ResponseEntity<String> response = restTemplate.postForEntity(FLOWISE_URL, request, String.class);

            String aiResult = "No diagnosis found";

            if (response.getBody() != null) {
                ObjectMapper mapper = new ObjectMapper();
                // Parse the outer JSON
                JsonNode root = mapper.readTree(response.getBody());
                // Extract the "text" field
                JsonNode textNode = root.path("text");
                if (!textNode.isMissingNode()) {
                    // The text field is itself JSON, so parse it again
                    JsonNode inner = mapper.readTree(textNode.asText());
                    JsonNode diagnosisNode = inner.path("diagnosis");
                    if (!diagnosisNode.isMissingNode()) {
                        aiResult = diagnosisNode.asText();
                    }
                }
            }

            // Add to model
            model.addAttribute("query", query);
            model.addAttribute("aiResponse", aiResult);

        } catch (Exception e) {
            model.addAttribute("query", query);
            model.addAttribute("aiResponse", "Error calling AI: " + e.getMessage());
        }

		return "symptom-ai";
	}
}
