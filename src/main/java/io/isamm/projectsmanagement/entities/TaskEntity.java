package io.isamm.projectsmanagement.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "TASKS", uniqueConstraints = @UniqueConstraint(columnNames = { "project_id", "reference" }))
public class TaskEntity extends GenericEntity {

	private static final long serialVersionUID = -8337655626205974035L;
	
	@NotBlank
	@Column(nullable = false)
	private String reference;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private ProjectEntity project;

	public TaskEntity() {
		super();
	}

	public TaskEntity(String id, String reference, ProjectEntity project, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(id, createdAt, updatedAt);
		this.reference = reference;
		this.project = project;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", reference=" + reference + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
