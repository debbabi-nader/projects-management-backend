package io.isamm.projectsmanagement.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.isamm.projectsmanagement.entities.ProjectEntity;
import io.isamm.projectsmanagement.repositories.ProjectRepository;
import io.isamm.projectsmanagement.services.ProjectService;


@Service
public class ProjectServiceImpl extends GenericServiceImpl<ProjectEntity> implements ProjectService {

	private final ProjectRepository projectRepository;
	
	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}

	@Override
	public Collection<ProjectEntity> getProjectsByManagerId(String id) {
		
		return this.projectRepository.getProjectsByManagerId(id);
		
	}

}
