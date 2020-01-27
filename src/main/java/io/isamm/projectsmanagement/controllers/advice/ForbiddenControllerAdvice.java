package io.isamm.projectsmanagement.controllers.advice;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.isamm.projectsmanagement.dtos.ErrorResponseDto;
import io.isamm.projectsmanagement.exceptions.AccountNotActiveException;
import io.isamm.projectsmanagement.exceptions.ForbiddenException;


@ControllerAdvice
public class ForbiddenControllerAdvice {
	
	@ExceptionHandler(value = { ForbiddenException.class })
	public ResponseEntity<ErrorResponseDto> handleForbiddenException(RuntimeException ex, HttpServletRequest request) {
				
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setStatus(HttpStatus.FORBIDDEN.value());
		errorResponseDto.setError("Forbidden");
		errorResponseDto.setPath(request.getRequestURL().toString());
		errorResponseDto.setTimestamp(LocalDateTime.now());
		
		ForbiddenException forbiddenException = (ForbiddenException) ex;
		if (forbiddenException instanceof AccountNotActiveException) {
			errorResponseDto.setMessage("Action forbidden");
		} else {
			errorResponseDto.setMessage("Account not active");
		}

		return new ResponseEntity<ErrorResponseDto> (errorResponseDto, new HttpHeaders(), HttpStatus.valueOf(errorResponseDto.getStatus()));

	}

}
