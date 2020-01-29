package io.isamm.projectsmanagement.controllers;

import java.util.Collection;

import javax.json.JsonPatch;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.isamm.projectsmanagement.entities.ProjectEntity;
import io.isamm.projectsmanagement.services.ProjectService;


@RestController
@RequestMapping(value = "/api/v1/projects")
public class ProjectController {

	private final ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}

	@RequestMapping(value = "/manager/{id}", method = RequestMethod.GET)
	public Collection<ProjectEntity> getProjectsByManagerId(@PathVariable(value = "id", required = true) String id) {
		return this.projectService.getProjectsByManagerId(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ProjectEntity getProjectById(@PathVariable(value = "id", required = true) String id) {
		return this.projectService.findById(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ProjectEntity createProject(@Valid @RequestBody ProjectEntity projectEntity) {
		return this.projectService.create(projectEntity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json-patch+json")
	public ProjectEntity partialUpdateProject(@RequestBody JsonPatch jsonPatch, @PathVariable(value = "id", required = true) String id) {
		return this.projectService.partialUpdate(jsonPatch, id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteProject(@PathVariable(value = "id", required = true) String id) {
		this.projectService.delete(id);
	};
	
}
