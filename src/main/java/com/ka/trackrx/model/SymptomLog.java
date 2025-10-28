package com.ka.trackrx.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class SymptomLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false)
	private String symptom;

	private Integer severity;

	@Column(length = 1000)
	private String notes;

	private LocalDate date;

	@Column(length = 2000)
	private String aiInsight;

	@PrePersist
	public void prePersist() {
		if (this.date == null) {
			this.date = LocalDate.now();
		}
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public String getSymptom() { return symptom; }
	public void setSymptom(String symptom) { this.symptom = symptom; }

	public Integer getSeverity() { return severity; }
	public void setSeverity(Integer severity) { this.severity = severity; }

	public String getNotes() { return notes; }
	public void setNotes(String notes) { this.notes = notes; }

	public LocalDate getDate() { return date; }
	public void setDate(LocalDate date) { this.date = date; }

	public String getAiInsight() { return aiInsight; }
	public void setAiInsight(String aiInsight) { this.aiInsight = aiInsight; }
}
