package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.User;

/**
 * The Interface UserRepository.
 * 
 * @author Radu
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	Optional<User> findByEmail(String email);

	/**
	 * Exists by email.
	 *
	 * @param email the email
	 * @return the boolean
	 */
	Boolean existsByEmail(String email);
}
