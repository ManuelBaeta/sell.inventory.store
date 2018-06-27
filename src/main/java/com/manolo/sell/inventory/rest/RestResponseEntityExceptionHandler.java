package com.manolo.sell.inventory.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.manolo.sell.inventory.domain.exception.BusinessException;
import com.manolo.sell.inventory.domain.exception.EntityForeignKeyNotFound;
import com.manolo.sell.inventory.domain.exception.EntityNotFoundException;
import com.manolo.sell.inventory.domain.exception.UnableToInsertException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	@ExceptionHandler({EntityNotFoundException.class, UnableToInsertException.class , EntityForeignKeyNotFound.class})
	protected ResponseEntity<Object> handleBusinessException (BusinessException ex, WebRequest request) {
		log.info("[RestResponseEntityExceptionHandler] Caught BusinessException. ");
		if (ex instanceof EntityNotFoundException) {
			return handleExceptionInternal(ex, new RestErrorMessage(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
			
		} else if (ex instanceof UnableToInsertException) {
			return handleExceptionInternal(ex, new RestErrorMessage(ex.getMessage()), new HttpHeaders(), HttpStatus.CONFLICT, request);
			
		} else if (ex instanceof EntityForeignKeyNotFound) {
			return handleExceptionInternal(ex, new RestErrorMessage(ex.getMessage()), new HttpHeaders(), HttpStatus.CONFLICT, request);
		}
	
		return handleExceptionInternal(ex, new RestErrorMessage("Unexpected business error"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	protected ResponseEntity<Object> handleUnexpectedError(Exception ex, WebRequest request) {
		log.info("[RestResponseEntityExceptionHandler] Caught unexpected error. Error msg: {}", ex);
		return handleExceptionInternal(ex, new RestErrorMessage("Unexpected error"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	public static class RestErrorMessage {
		
		private String errorMessage;
		
		public RestErrorMessage() {}
		
		public RestErrorMessage(String msg) {
			this.errorMessage = msg;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
	}
}
