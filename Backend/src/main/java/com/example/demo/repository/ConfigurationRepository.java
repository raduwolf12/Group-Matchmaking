package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}
