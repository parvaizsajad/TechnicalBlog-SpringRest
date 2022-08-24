package com.techblog.payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

	private Integer postId;
	@NotEmpty(message = "title shout not be empty")
	private String postTitle;
	@NotEmpty(message = "content shout not be empty")
	@Size(min = 8, message = "minimum length is {min}")
	private String postContent;
	private String imageName;
	private Date postDate;
	private CategoryDto category;
	private UserDto user;
	List<CommentDto> commentList=new ArrayList<>();
}
