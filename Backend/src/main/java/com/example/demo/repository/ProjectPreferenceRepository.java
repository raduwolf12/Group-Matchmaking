package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.ProjectPreference;

/**
 * The Interface ProjectPreferenceRepository.
 */
@Repository
public interface ProjectPreferenceRepository extends JpaRepository<ProjectPreference, Long> {

}
