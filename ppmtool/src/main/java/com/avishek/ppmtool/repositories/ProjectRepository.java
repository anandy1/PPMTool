package com.avishek.ppmtool.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.avishek.ppmtool.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	Project findByProjectIdentifier(String projectId);

	Iterable<Project> findAll();
	
	
}
