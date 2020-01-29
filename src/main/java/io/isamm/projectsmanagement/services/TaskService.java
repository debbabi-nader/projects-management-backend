package io.isamm.projectsmanagement.services;

import java.util.Collection;

import io.isamm.projectsmanagement.entities.TaskEntity;


public interface TaskService extends GenericService<TaskEntity> {

	public Collection<TaskEntity> getTasksByProjectId(String id);
	
	public void deleteTasksByProjectId(String id);

}
