package com.techblog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourcenotFound(ResourceNotFoundException res) {
		String fieldName = res.getFieldName();

		ApiResponse apiResponse = new ApiResponse(res.getMessage() + fieldName, false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> ConstraintViolationException(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String field = ((FieldError) error).getField();
			String defaultMessage = error.getDefaultMessage();
			errorMap.put(field, defaultMessage);
		});
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CategoryNotAvailableException.class)
	public ResponseEntity<?> categoryNotAvailableException(CategoryNotAvailableException ex) {
		String name = ex.getCategoryName();
		int value = ex.getCategoryValue();
		String message = ex.getMessage();
		System.out.println(name + "  ---" + value + "---" + message);
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> ConstraintViolationException(Exception ex) {
		String message = ex.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(jwtException.class)
	public ResponseEntity<?> jwtException(jwtException res) {
		String fieldName = res.getMessage();

		ApiResponse apiResponse = new ApiResponse(res.getMessage() + fieldName, false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

}
