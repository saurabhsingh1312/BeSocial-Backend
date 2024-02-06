package com.web.socialweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
	
	public ResponseEntity<String> handler(Exception ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.ACCEPTED);
	}
}
