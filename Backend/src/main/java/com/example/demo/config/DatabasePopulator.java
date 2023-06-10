package com.example.demo.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.entity.Configuration;
import com.example.demo.model.entity.PairPreference;
import com.example.demo.model.entity.Project;
import com.example.demo.model.entity.ProjectPreference;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.Role;
import com.example.demo.repository.ConfigurationRepository;
import com.example.demo.repository.GroupPreferenceRepository;
import com.example.demo.repository.ProjectPreferenceRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;

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

	/** The project repository. */
	@Autowired
	private ProjectRepository projectRepository;

	/** The preference repository. */
	@Autowired
	private ProjectPreferenceRepository preferenceRepository;

	@Autowired
	private GroupPreferenceRepository pairPreferenceRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private ProjectPreferenceRepository projectPreferenceRepository;

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Value("${default.pair.size}")
	private int defaultPairSize;

	@Value("${default.group.size}")
	private int defaultGroupSize;

	/**
	 * Populate database.
	 */
	@PostConstruct
	public void populateDatabase() {
		if (configurationRepository.count() == 0) {
			// Create a new configuration object with default values
			Configuration configuration = new Configuration();
			configuration.setPairSize(defaultPairSize);
			configuration.setGroupSize(defaultGroupSize);

			configurationRepository.save(configuration);
		}

		if (this.userRepository.count() == 0) {
			List<User> users = new ArrayList<>();
			for (int i = 1; i <= 16; i++) {
				User user = new User();
				user.setName("User " + i);
				user.setEmail("user" + i + "@yahoo.com");
				user.setPassword(encoder.encode("testpass"));
				user.setPasswordTemporary("testpass");
				user.setRole(Role.STUDENT);
				userRepository.save(user);
				users.add(user);
			}
			// Create and save projects
			List<Project> projects = new ArrayList<>();
			for (int i = 1; i <= 5; i++) {
				Project project = new Project();
				project.setTitle("Project " + i);
				project.setDescription("Test project " + i + " description");
				project.setOwner(users.get(i - 1));
				project.setSize(8L);
				project.setVisibility(true);
				projectRepository.save(project);
				projects.add(project);

			}

			// Create and save pair preferences
//			for (int i = 0; i < 5; i++) {
//				User user1 = users.get(i);
//				User user2 = users.get(i + 5);
//				PairPreference pairPreferences = new PairPreference();
//				pairPreferences.setGroupCreator(user1);
//
//				List<User> pairList = new ArrayList<User>();
//				pairList.add(user1);
//				pairList.add(user2);
//
//				pairPreferences.setUsers(pairList);
//				pairPreferenceRepository.save(pairPreferences);
//			}
			List<Project> allProjects = projectRepository.findAll();

			for (int i = 0; i < 5; i++) {
				User user1 = users.get(i);
				User user2 = users.get(i + 5);
				PairPreference pairPreferences = new PairPreference();
				pairPreferences.setGroupCreator(user1);

				List<User> pairList = new ArrayList<User>();
				pairList.add(user1);
				pairList.add(user2);

				pairPreferences.setUsers(pairList);
				pairPreferenceRepository.save(pairPreferences);
				int rank = 1;
				Collections.shuffle(allProjects);
				for (Project project : allProjects) {
					ProjectPreference preference1 = new ProjectPreference();
					preference1.setUser(user1);
					preference1.setProject(project);
					preference1.setRank(rank);
					projectPreferenceRepository.save(preference1);

					rank++;
				}
			}
			for (int i = 11; i <= 15; i++) {
				User user1 = users.get(i);
				PairPreference pairPreferences = new PairPreference();
				pairPreferences.setGroupCreator(user1);

				List<User> pairList = new ArrayList<User>();
				pairList.add(user1);

				pairPreferences.setUsers(pairList);
				pairPreferenceRepository.save(pairPreferences);
				int rank = 1;
				Collections.shuffle(allProjects);
				for (Project project : allProjects) {
					ProjectPreference preference1 = new ProjectPreference();
					preference1.setUser(user1);
					preference1.setProject(project);
					preference1.setRank(rank);
					projectPreferenceRepository.save(preference1);

					rank++;
				}
			}

			User student = new User();
			student.setName("whx");
			student.setCanvasUserId(1L);
			student.setGroupId(1L);
			student.setEmail("whx862@alumni.ku.dk");
			student.setPassword(encoder.encode("TESTUSERPASS"));
			student.setPasswordTemporary("TESTUSERPASS");

//			student.setUserId(1L);
			student.setRole(Role.STUDENT);
			userRepository.save(student);

			User teacher = new User();
			teacher.setName("qwe");
			teacher.setEmail("qwe@alumni.ku.dk");

			teacher.setCanvasUserId(2L);
			teacher.setGroupId(2L);
//			teacher.setUserId(2L);
			teacher.setRole(Role.TEACHER);
			teacher.setPassword(encoder.encode("TESTUSERPASS"));
			teacher.setPasswordTemporary("TESTUSERPASS");

			User professor = new User();
			professor.setName("abc");
			professor.setEmail("abc@alumni.ku.dk");

			professor.setCanvasUserId(3L);
			professor.setGroupId(3L);
//			professor.setUserId(3L);
			professor.setRole(Role.PROFESSOR);
			professor.setPassword(encoder.encode("TESTUSERPASS"));
			professor.setPasswordTemporary("TESTUSERPASS");

			Project project = new Project();
			project.setDescription("Test proj desc");
			project.setOwner(professor);
			project.setSize(8L);
			project.setTitle("Test project");
			project.setVisibility(true);

			ProjectPreference preference = new ProjectPreference();
			preference.setProject(project);
			preference.setRank(1);
			preference.setUser(student);

			userRepository.save(teacher);
			userRepository.save(professor);

			projectRepository.save(project);
			preferenceRepository.save(preference);

		}

	}

}