package io.isamm.projectsmanagement.services.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.json.JsonPatch;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.isamm.projectsmanagement.entities.GenericEntity;
import io.isamm.projectsmanagement.exceptions.BadRequestException;
import io.isamm.projectsmanagement.exceptions.ForeignKeyIntegrityViolationException;
import io.isamm.projectsmanagement.exceptions.MissingRequiredArgumentException;
import io.isamm.projectsmanagement.exceptions.ResourceNotFoundException;
import io.isamm.projectsmanagement.exceptions.UniqueConstraintViolationException;
import io.isamm.projectsmanagement.services.GenericService;
import io.isamm.projectsmanagement.utils.JsonPatchUtil;


public class GenericServiceImpl<T extends GenericEntity> implements GenericService<T> {

	@Autowired
	private JpaRepository<T, String> repository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private Validator validator;

	@Override
	public T findById(String id) {
		
		if (id == null || id.trim().equals(""))
			throw new MissingRequiredArgumentException();
		Optional<T> entity = this.repository.findById(id);
		if (!entity.isPresent())
			throw new ResourceNotFoundException(id);
		return entity.get();

	}

	@Override
	public Collection<T> findAll() {

		return this.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
	
	}
	
	@Override
	public Collection<T> findAll(Sort sort) {
				
		return this.repository.findAll(sort);

	}
	
	private T persist(T t) {
		
		try {
			return this.repository.saveAndFlush(t);
		} catch (DataIntegrityViolationException e) {
			if (e.getMostSpecificCause().getClass().getName().equals("org.postgresql.util.PSQLException") && ((SQLException) e.getMostSpecificCause()).getSQLState().equals("23505"))
				throw new UniqueConstraintViolationException(e.getMostSpecificCause());
			throw new BadRequestException(e);
		}
		
	}

	@Transactional
	@Override
	public T create(T t) {
		
		t.setId(UUID.randomUUID().toString());
		return this.persist(t);
		
	}

	@Transactional
	@Override
	public T partialUpdate(JsonPatch jsonPatch, String id) {
		
		T existingEntity = this.findById(id);
		@SuppressWarnings("unchecked")
		T patchedEntity = JsonPatchUtil.patch(jsonPatch, existingEntity, (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0], this.objectMapper);
		patchedEntity.setId(existingEntity.getId());
		patchedEntity.setCreatedAt(existingEntity.getCreatedAt());
		patchedEntity.setUpdatedAt(existingEntity.getUpdatedAt());
		Set<ConstraintViolation<T>> violations = this.validator.validate(patchedEntity);
        if (!violations.isEmpty())
        	throw new ConstraintViolationException(violations);
		return this.persist(patchedEntity);

	}

	@Transactional
	@Override
	public void delete(String id) {
		
		if (id == null || id.trim().equals(""))
			throw new MissingRequiredArgumentException();
		try {
			this.repository.deleteById(id);
			this.repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			if (e.getMostSpecificCause().getClass().getName().equals("org.postgresql.util.PSQLException") && ((SQLException) e.getMostSpecificCause()).getSQLState().equals("23503"))
				throw new ForeignKeyIntegrityViolationException(e.getMostSpecificCause());
			throw new BadRequestException(e);
		}

	}

}
