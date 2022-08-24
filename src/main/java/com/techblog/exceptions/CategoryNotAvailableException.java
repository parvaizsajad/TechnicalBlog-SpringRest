package com.techblog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryNotAvailableException extends RuntimeException{

	private static final long serialVersionUID = 3262136522252431519L;
	private String CategoryName;
	private int CategoryValue;

	public CategoryNotAvailableException(String categoryName, int categoryValue) {
		super(String.format("%s not found with %d",categoryName,categoryValue));
		CategoryName = categoryName;
		CategoryValue = categoryValue;
	}
	
	
	
}
