package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Project;
import com.example.demo.model.entity.User;

/**
 * The Interface ProjectRepository.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	/**
	 * Find by owner.
	 *
	 * @param owner the owner
	 * @return the list
	 */
	List<Project> findByOwner(User owner);

}
