package io.isamm.projectsmanagement.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.isamm.projectsmanagement.entities.TaskEntity;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String> {

	@Query("SELECT task FROM TaskEntity AS task WHERE task.project.id LIKE :id ORDER BY task.createdAt DESC")
	public Collection<TaskEntity> getTasksByProjectId(@Param("id") String id);
	
	@Modifying
	@Query("DELETE FROM TaskEntity AS task WHERE task.project.id LIKE :id")
	public void deleteTasksByProjectId(@Param("id") String id);

}
