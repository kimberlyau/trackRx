package com.ka.trackrx.model;

import jakarta.persistence.*;

@Entity
public class Medication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false)
	private String name;

	private String dosage;
	private String frequency;
	@Column(length = 1000)
	private String notes;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getDosage() { return dosage; }
	public void setDosage(String dosage) { this.dosage = dosage; }

	public String getFrequency() { return frequency; }
	public void setFrequency(String frequency) { this.frequency = frequency; }

	public String getNotes() { return notes; }
	public void setNotes(String notes) { this.notes = notes; }
}
