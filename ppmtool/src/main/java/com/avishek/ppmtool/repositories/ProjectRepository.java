package com.avishek.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;

import com.avishek.ppmtool.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
