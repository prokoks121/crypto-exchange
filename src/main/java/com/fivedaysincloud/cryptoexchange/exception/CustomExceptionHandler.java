package com.office.exchange.exception;

import java.nio.file.AccessDeniedException;
import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
class CustomExceptionHandler extends BaseExceptionHandler {

	public CustomExceptionHandler() {
        registerMapping(InvalidParameterException.class,HttpStatus.BAD_REQUEST);
        registerMapping(AccessDeniedException.class, HttpStatus.UNAUTHORIZED);
		registerMapping(EntityNotFoundException.class, HttpStatus.NOT_FOUND);
        registerMapping(ReservedServiceException.class, HttpStatus.BAD_REQUEST);
        registerMapping(AccessDeniedException.class, HttpStatus.UNAUTHORIZED);
		registerMapping(NoSuchElementException.class, HttpStatus.NOT_FOUND);
	}
}