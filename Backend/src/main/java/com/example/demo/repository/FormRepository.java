package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Form;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

	@Query("SELECT f FROM Form f WHERE f.isOpen = true AND f.openingTime < :time")
	List<Form> findOpenFormsBeforeTime(@Param("time") LocalDateTime time);

}
