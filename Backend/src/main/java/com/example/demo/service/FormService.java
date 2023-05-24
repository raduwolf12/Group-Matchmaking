package com.example.demo.service;

public interface FormService {

	void createForm(int duration);

	void closeForm();

	boolean isFormOpen();

	void closeFormManualy();

}
