package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.Role;
import com.example.demo.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabasePopulator.
 */
@Component
public class DatabasePopulator {

	/** The entity manager. */
	@Lazy
	@Autowired
	private EntityManager entityManager;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Populate database.
	 */
	@PostConstruct
	public void populateDatabase() {

		if (this.userRepository.count() == 0) {
			User student = new User();
			student.setName("whx");
			student.setCanvasUserId(1L);
			student.setGroupId(1L);
			student.setPreferenceId(1L);
			student.setUserId(1L);
			student.setRole(Role.STUDENT);

			User teacher = new User();
			teacher.setName("qwe");
			teacher.setCanvasUserId(2L);
			teacher.setGroupId(2L);
			teacher.setPreferenceId(2L);
			teacher.setUserId(2L);
			teacher.setRole(Role.TEACHER);

			User professor = new User();
			professor.setName("abc");
			professor.setCanvasUserId(3L);
			professor.setGroupId(3L);
			professor.setPreferenceId(3L);
			professor.setUserId(3L);
			professor.setRole(Role.PROFESSOR);

			userRepository.save(student);
			userRepository.save(teacher);
			userRepository.save(professor);

		}

	}

}