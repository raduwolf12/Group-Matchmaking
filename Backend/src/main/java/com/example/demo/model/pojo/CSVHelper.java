package com.example.demo.model.pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.CSVConfig;
import com.example.demo.model.entity.User;
import com.example.demo.model.entity.enums.Role;
import com.example.demo.utils.PasswordGenerator;

/**
 * The Class CSVHelper.
 */
public class CSVHelper {

	/** The type. */
	public static String TYPE = "text/csv";

	/** The HEADE rs. */
	static String[] HEADERs = { "name", "canvas_user_id", "user_id", "login_id", "sections", "group_name",
			"canvas_group_id", "group_id" };

//	private static CSVConfig csvConfig;
//
//    public static void setCSVConfig(CSVConfig config) {
//        csvConfig = config;
//    }
    
    @Value("${app.passwordLength}")
    private static int length;

	/**
	 * Checks for CSV format.
	 *
	 * @param file the file
	 * @return true, if successful
	 */
	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	/**
	 * Csv to tutorials.
	 *
	 * @param is the is
	 * @return the list
	 */
	public static List<User> csvToTutorials(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<User> users = new ArrayList<User>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				User user = new User();
				user.setName(csvRecord.get("name"));
				user.setCanvasUserId(Long.parseLong(csvRecord.get("canvas_user_id")));
				user.setUserId(Long.parseLong(csvRecord.get("user_id")));
				user.setGroupId(null);
//				user.setPreferenceId(null);
				user.setRole(Role.STUDENT);
	            user.setPassword(PasswordGenerator.generateRandomPassword(length));
				users.add(user);
			}

			return users;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

}
