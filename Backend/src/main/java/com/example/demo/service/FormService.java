package com.example.demo.service;

/**
 * The Interface FormService.
 */
public interface FormService {

	/**
	 * Creates the form.
	 *
	 * @param duration the duration
	 */
	void createForm(int duration);

	/**
	 * Close form.
	 */
	void closeForm();

	/**
	 * Checks if is form open.
	 *
	 * @return true, if is form open
	 */
	boolean isFormOpen();

	/**
	 * Close form manualy.
	 */
	void closeFormManualy();

}
