package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.PairPreference;
import com.example.demo.model.entity.User;

/**
 * The Interface GroupPreferenceServiceRepository.
 */
@Repository
public interface GroupPreferenceRepository extends JpaRepository<PairPreference, Long> {

	/**
	 * Find by user.
	 *
	 * @param userId the user id
	 * @return the optional
	 */

	@Query(value = "SELECT p.* FROM pair_preferences p "
			+ "JOIN pair_preferences_users pu ON pu.pair_preferences_id = p.group_preference_id "
			+ "WHERE pu.user_id = :userId", nativeQuery = true)
	Optional<PairPreference> findByUserId(Long userId);
}
