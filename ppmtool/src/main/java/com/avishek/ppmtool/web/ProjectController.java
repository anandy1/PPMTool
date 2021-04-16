package com.avishek.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avishek.ppmtool.domain.Project;
import com.avishek.ppmtool.services.MapValidationErrorService;
import com.avishek.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.validate(result);
		if(errorMap != null) {
			return errorMap;
		}
		return new ResponseEntity<Project>(projectService.saveOrUpdateProject(project), HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectId}")
	public ResponseEntity<?> findProjectById(@PathVariable String projectId) {
		return new ResponseEntity<Project>(projectService.findProjectByIdentifire(projectId), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> findAllProjects() {
		return projectService.findAllProjects();
	}
	
	@DeleteMapping("{projectId}")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
		projectService.deleteProjectByIdentifire(projectId);
		return new ResponseEntity<String>("Project was deleted", HttpStatus.OK);
	}

}
