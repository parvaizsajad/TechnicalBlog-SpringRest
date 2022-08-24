package com.techblog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResourceNotFoundException extends RuntimeException {

	
	
	
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private int fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
		super(String.format("%s not found with %s and %d",resourceName,fieldName,fieldValue));
		
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		
	}
	
	
	
	

}
