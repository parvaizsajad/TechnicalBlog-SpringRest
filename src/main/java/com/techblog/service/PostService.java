package com.techblog.service;

import java.util.List;

import com.techblog.helper.PostResponse;
import com.techblog.payload.PostDto;

public interface PostService {

	// create

	PostDto createPost(PostDto postDto, Integer userId, Integer CategoryId);

	// update

	PostDto updatePost(Integer id, PostDto postDto);

	// delete post

	void deletePost(Integer id);

	// get All post
	PostResponse getPosts(Integer pageNumber,Integer pageSize);

	// get post by category

	List<PostDto> getPostByCategory(Integer CategoryId);

	// Get Posts by User

	List<PostDto> getPostByUser(Integer userId);

	// get all post by search keyword
	List<PostDto> searchPosts(String keyword);

	// get 1 post
	PostDto getbyId(Integer id);

//	PostResponse getSorted(Integer pageNumber, Integer pageSize, String sortedBy);

	PostResponse getSorted(Integer pageNumber, Integer pageSize, String sortedBy, String dir);

	
	//search by content
	List<PostDto> searchContent(String keyword);



}
