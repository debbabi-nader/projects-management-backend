package io.isamm.projectsmanagement.services;

import java.util.Collection;

import io.isamm.projectsmanagement.entities.ProjectEntity;


public interface ProjectService extends GenericService<ProjectEntity> {

	public Collection<ProjectEntity> getProjectsByManagerId(String id);

}
