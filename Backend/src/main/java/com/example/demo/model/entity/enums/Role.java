package com.example.demo.model.entity.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * The Enum Role.
 * 
 * @author Radu
 */
public enum Role implements GrantedAuthority {

	/** The student. */
	STUDENT,
	/** The teacher. */
	TEACHER,
	/** The professor. */
	PROFESSOR;

	@Override
	public String getAuthority() {
		return name();
	}
}
