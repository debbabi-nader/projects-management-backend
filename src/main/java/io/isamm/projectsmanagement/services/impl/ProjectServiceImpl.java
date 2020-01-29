package io.isamm.projectsmanagement.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.isamm.projectsmanagement.entities.ProjectEntity;
import io.isamm.projectsmanagement.repositories.ProjectRepository;
import io.isamm.projectsmanagement.services.ProjectService;
import io.isamm.projectsmanagement.services.TaskService;


@Service
public class ProjectServiceImpl extends GenericServiceImpl<ProjectEntity> implements ProjectService {

	private final ProjectRepository projectRepository;
	private final TaskService taskService;
	
	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository, TaskService taskService) {
		super();
		this.projectRepository = projectRepository;
		this.taskService = taskService;
	}

	@Override
	public Collection<ProjectEntity> getProjectsByManagerId(String id) {
		
		return this.projectRepository.getProjectsByManagerId(id);
		
	}

	@Transactional
	@Override
	public void delete(String id) {
		this.taskService.deleteTasksByProjectId(id);
		super.delete(id);
	}

}
