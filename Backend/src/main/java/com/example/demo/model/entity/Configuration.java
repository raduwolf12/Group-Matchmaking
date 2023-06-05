package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Class Configuration.
 */
@Entity
@Table(name = "configuration")
public class Configuration {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The pair size. */
	@Column(name = "pair_size")
	private int pairSize;

	/** The group size. */
	@Column(name = "group_size")
	private int groupSize;

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
	 * Gets the pair size.
	 *
	 * @return the pair size
	 */
	public int getPairSize() {
		return pairSize;
	}

	/**
	 * Sets the pair size.
	 *
	 * @param pairSize the new pair size
	 */
	public void setPairSize(int pairSize) {
		this.pairSize = pairSize;
	}

	/**
	 * Gets the group size.
	 *
	 * @return the group size
	 */
	public int getGroupSize() {
		return groupSize;
	}

	/**
	 * Sets the group size.
	 *
	 * @param groupSize the new group size
	 */
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

}