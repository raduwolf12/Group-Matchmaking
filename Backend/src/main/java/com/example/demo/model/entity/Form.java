package com.example.demo.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The Class Form.
 */
@Entity
public class Form {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The is open. */
	private boolean isOpen;

	/** The opening time. */
	private LocalDateTime openingTime;

	/** The duration in days. */
	private int durationInDays;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Checks if is open.
	 *
	 * @return true, if is open
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * Sets the open.
	 *
	 * @param isOpen the new open
	 */
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	/**
	 * Gets the opening time.
	 *
	 * @return the opening time
	 */
	public LocalDateTime getOpeningTime() {
		return openingTime;
	}

	/**
	 * Sets the opening time.
	 *
	 * @param openingTime the new opening time
	 */
	public void setOpeningTime(LocalDateTime openingTime) {
		this.openingTime = openingTime;
	}

	/**
	 * Gets the duration in days.
	 *
	 * @return the duration in days
	 */
	public int getDurationInDays() {
		return durationInDays;
	}

	/**
	 * Sets the duration in days.
	 *
	 * @param durationInDays the new duration in days
	 */
	public void setDurationInDays(int durationInDays) {
		this.durationInDays = durationInDays;
	}

}