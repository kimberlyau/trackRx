package com.ka.trackrx.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HealthLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(name = "user_id")
	private User user;

	private Integer sleepHours;
	private Integer waterIntake;
	private String mood;
	private LocalDate date;

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

	public Integer getSleepHours() { return sleepHours; }
	public void setSleepHours(Integer sleepHours) { this.sleepHours = sleepHours; }

	public Integer getWaterIntake() { return waterIntake; }
	public void setWaterIntake(Integer waterIntake) { this.waterIntake = waterIntake; }

	public String getMood() { return mood; }
	public void setMood(String mood) { this.mood = mood; }

	public LocalDate getDate() { return date; }
	public void setDate(LocalDate date) { this.date = date; }
}
