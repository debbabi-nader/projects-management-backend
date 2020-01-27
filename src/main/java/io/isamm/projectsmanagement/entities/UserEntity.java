package io.isamm.projectsmanagement.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import io.isamm.projectsmanagement.enumerations.ProfileTypeEnum;


@Entity
@Table(name = "USERS")
public class UserEntity extends GenericEntity {

	private static final long serialVersionUID = 8291006034082629988L;
	
	@Enumerated(EnumType.STRING)
	@JoinColumn(nullable = false)
	private ProfileTypeEnum profileType;
	
	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotBlank
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private boolean isActive;
	
	@NotBlank
	@Column(nullable = false)
	private String firstName;
	
	@NotBlank
	@Column(nullable = false)
	private String lastName;

	public UserEntity() {
		super();
	}

	public UserEntity(String id, ProfileTypeEnum profileType, String email, String password, boolean isActive, 
			String firstName, String lastName, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super(id, createdAt, updatedAt);
		this.profileType = profileType;
		this.email = email;
		this.password = password;
		this.isActive = isActive;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public ProfileTypeEnum getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileTypeEnum profileType) {
		this.profileType = profileType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", profileType=" + profileType + ", email=" + email + ", password=" + password + ", isActive=" + isActive + 
				", firstName=" + firstName + ", lastName=" + lastName + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
