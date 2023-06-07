package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.ProjectPreference;

/**
 * The Interface ProjectPreferenceRepository.
 */
@Repository
public interface ProjectPreferenceRepository extends JpaRepository<ProjectPreference, Long> {

	/**
	 * Gets the preferences by user id.
	 *
	 * @param id the id
	 * @return the preferences by user id
	 */
	@Query(nativeQuery = true, value = "select * from project_preferences where user_id = :id")
	List<ProjectPreference> getPreferencesByUserId(Long id);

}
