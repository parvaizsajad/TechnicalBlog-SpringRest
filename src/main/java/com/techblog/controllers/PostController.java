package com.techblog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techblog.exceptions.ApiResponse;
import com.techblog.helper.PostResponse;
import com.techblog.helper.StaticConstants;
import com.techblog.payload.PostDto;
import com.techblog.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	// save post

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId) {

		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}

	// get post by category

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<?> getByCategory(@PathVariable("categoryId") Integer categoryId) {
		List<PostDto> postByCategory = this.postService.getPostByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(postByCategory, HttpStatus.OK);

	}

	// get posts by user

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getByUser(@PathVariable("userId") Integer userId) {

		List<PostDto> postByCategory = this.postService.getPostByUser(userId);

		return new ResponseEntity<List<PostDto>>(postByCategory, HttpStatus.OK);

	}

	// delete post
	@DeleteMapping("/delete{postId}")
	public ResponseEntity<?> deletePost(@PathVariable("postId") Integer postId) {

		this.postService.deletePost(postId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("The Post with id:" + postId + " is deleted", true),
				HttpStatus.OK);

	}

	@GetMapping("/get")
	public ResponseEntity<?> getAllPosts(@RequestParam(value = "pageNumber",defaultValue =StaticConstants.PAGE_NUMBER , required = false ) Integer pageNumber,
				@RequestParam(value = "pageSize",defaultValue = StaticConstants.PAGE_SIZE, required = false ) Integer pageSize ) {

			System.out.println(pageNumber+"ggggg"+pageSize);
			PostResponse posts = this.postService.getPosts(pageNumber, pageSize);

			return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);

		}
	
	@GetMapping("/sort")
	public ResponseEntity<?> getAllSorted(@RequestParam(value = "pageNumber", required = false ) Integer pageNumber,
				@RequestParam(value = "pageSize", required = false ) Integer pageSize,
				@RequestParam(value = "sortedBy", required = false ) String sortedBy,
				@RequestParam(value = "dir", required = false ) String dir) {
			PostResponse posts = this.postService.getSorted(pageNumber, pageSize,sortedBy,dir);

			return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);

		}

	@GetMapping("/search/{keyword}")
	public ResponseEntity<?> searchPost(@PathVariable("keyword") String keyword) {
		List<PostDto> searchPosts = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);
	}
	
	@GetMapping("/search/content/{keyword}")
	public ResponseEntity<?> searchContent(@PathVariable("keyword") String keyword) {
		List<PostDto> searchPosts = this.postService.searchContent(keyword);
		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);
	}

}
