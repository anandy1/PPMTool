package com.avishek.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avishek.ppmtool.domain.Project;
import com.avishek.ppmtool.exceptions.ProjectIdException;
import com.avishek.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch(Exception e) {
			throw new ProjectIdException("Project Id '" +project.getProjectIdentifier().toUpperCase()+ "' already present");
		}
	}
	
	public Project findProjectByIdentifire(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project Id not present");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifire(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project == null) {
			throw new ProjectIdException("Project Id '"+projectId+"' not present, can not be deleted.");
		}
		projectRepository.delete(project);
	}

}
