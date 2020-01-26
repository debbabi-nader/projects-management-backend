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
@Table(name = "PROJECTS", uniqueConstraints = @UniqueConstraint(columnNames = { "manager_id", "reference" }))
public class ProjectEntity extends GenericEntity {

	private static final long serialVersionUID = 2849944777872280436L;
	
	@NotBlank
	@Column(nullable = false)
	private String reference;
	
	@NotBlank
	@Column(nullable = false)
	private String description;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private UserEntity manager;
	
	public ProjectEntity() {
		super();
	}

	public ProjectEntity(String id, String reference, String description, UserEntity manager, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(id, createdAt, updatedAt);
		this.reference = reference;
		this.description = description;
		this.manager = manager;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getManager() {
		return manager;
	}

	public void setManager(UserEntity manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", reference=" + reference + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
