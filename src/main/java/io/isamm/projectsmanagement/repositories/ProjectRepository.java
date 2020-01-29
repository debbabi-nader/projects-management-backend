package io.isamm.projectsmanagement.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.isamm.projectsmanagement.entities.ProjectEntity;


@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, String> {

	@Query("SELECT project FROM ProjectEntity AS project WHERE project.manager.id LIKE :id")
	public Collection<ProjectEntity> getProjectsByManagerId(@Param("id") String id);

}
