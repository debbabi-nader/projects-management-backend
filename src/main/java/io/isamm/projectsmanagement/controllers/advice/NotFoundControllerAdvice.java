package io.isamm.projectsmanagement.controllers.advice;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.isamm.projectsmanagement.dtos.ErrorResponseDto;
import io.isamm.projectsmanagement.exceptions.NotFoundException;
import io.isamm.projectsmanagement.exceptions.ResourceNotFoundException;


@ControllerAdvice
public class NotFoundControllerAdvice {
	
	@ExceptionHandler(value = { NotFoundException.class })
	public ResponseEntity<ErrorResponseDto> handleNotFoundException(RuntimeException ex, HttpServletRequest request) {
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponseDto.setError("Not found");
		errorResponseDto.setPath(request.getRequestURL().toString());
		errorResponseDto.setTimestamp(LocalDateTime.now());
		
		NotFoundException notFoundException = (NotFoundException) ex;
		if (notFoundException instanceof ResourceNotFoundException) {
			ResourceNotFoundException resourceNotFoundException = (ResourceNotFoundException) notFoundException;
			errorResponseDto.setMessage("No record was found for the given id: " + resourceNotFoundException.getId());
		} else {
			errorResponseDto.setMessage("Not found");
		}
		
		return new ResponseEntity<ErrorResponseDto> (errorResponseDto, new HttpHeaders(), HttpStatus.valueOf(errorResponseDto.getStatus()));
		
	}

}
