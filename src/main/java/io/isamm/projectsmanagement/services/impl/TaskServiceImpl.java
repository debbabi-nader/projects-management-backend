package io.isamm.projectsmanagement.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.isamm.projectsmanagement.entities.TaskEntity;
import io.isamm.projectsmanagement.repositories.TaskRepository;
import io.isamm.projectsmanagement.services.TaskService;


@Service
public class TaskServiceImpl extends GenericServiceImpl<TaskEntity> implements TaskService {

	private final TaskRepository taskRepository;
	
	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public Collection<TaskEntity> getTasksByProjectId(String id) {
		return this.taskRepository.getTasksByProjectId(id);
	}

	@Transactional
	@Override
	public void deleteTasksByProjectId(String id) {
		this.taskRepository.deleteTasksByProjectId(id);
	}

}
