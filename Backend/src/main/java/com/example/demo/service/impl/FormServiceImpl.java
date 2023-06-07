package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Form;
import com.example.demo.repository.FormRepository;
import com.example.demo.service.FormService;

/**
 * The Class FormServiceImpl.
 */
@Service
public class FormServiceImpl implements FormService {

	/** The form repository. */
	@Autowired
	FormRepository formRepository;

	/**
	 * Creates the form.
	 *
	 * @param duration the duration
	 */
	@Override
	public void createForm(int duration) {
		LocalDateTime now = LocalDateTime.now();
		Form form = new Form();
		form.setDurationInDays(duration);
		form.setOpen(true);
		form.setOpeningTime(now);
		formRepository.save(form);
	}

	/**
	 * Close form.
	 */
	@Override
	public void closeForm() {
		LocalDateTime now = LocalDateTime.now();

		List<Form> formsToClose = formRepository.findOpenFormsBeforeTime(now.minusDays(1));

		for (Form form : formsToClose) {
			if (form.getOpeningTime().plusDays(form.getDurationInDays()).isAfter(now))
				continue;
			form.setOpen(false);
			formRepository.save(form);
		}
	}

	/**
	 * Close form manualy.
	 */
	@Override
	public void closeFormManualy() {
		Form form = formRepository.findAll().get(0);
		form.setOpen(false);
		formRepository.save(form);

	}

	/**
	 * Checks if is form open.
	 *
	 * @return true, if is form open
	 */
	@Override
	public boolean isFormOpen() {
		Form form = formRepository.findAll().get(0);
		return form.isOpen();
	}

}
