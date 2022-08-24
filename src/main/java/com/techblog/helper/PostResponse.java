package com.techblog.helper;

import java.util.List;

import com.techblog.payload.PostDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	private List<PostDto>  content;
	private int pageNumber;
	private int pageSize;
	private long pageElements;
	private int totalPages;
	private boolean isLastPage;
}
