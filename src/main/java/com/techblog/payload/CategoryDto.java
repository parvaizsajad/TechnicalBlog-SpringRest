package com.techblog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class CategoryDto {

	
	private Integer categoryId;
	@NotEmpty(message = "Title should not be empty")
	@Size(min = 3,message = "min size should be {min}")
	private String categoryTitle;
	@NotEmpty(message = "Title should not be empty")
	@Size(min = 3,message = "min size should be {min}")
	private String categoryDescription;
}
