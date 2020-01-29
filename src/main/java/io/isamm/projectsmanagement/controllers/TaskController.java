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

import io.isamm.projectsmanagement.entities.TaskEntity;
import io.isamm.projectsmanagement.services.TaskService;


@RestController
@RequestMapping(value = "/api/v1/tasks")
public class TaskController {

	private final TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}

	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public Collection<TaskEntity> getTasksByProjectId(@PathVariable(value = "id", required = true) String id) {
		return this.taskService.getTasksByProjectId(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public TaskEntity getTaskById(@PathVariable(value = "id", required = true) String id) {
		return this.taskService.findById(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public TaskEntity createTask(@Valid @RequestBody TaskEntity taskEntity) {
		return this.taskService.create(taskEntity);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json-patch+json")
	public TaskEntity partialUpdateTask(@RequestBody JsonPatch jsonPatch, @PathVariable(value = "id", required = true) String id) {
		return this.taskService.partialUpdate(jsonPatch, id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteTask(@PathVariable(value = "id", required = true) String id) {
		this.taskService.delete(id);
	}
	
}
