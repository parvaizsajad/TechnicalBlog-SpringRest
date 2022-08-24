package com.techblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techblog.payload.CommentDto;
import com.techblog.service.CommentService;

@RestController
@RequestMapping(path = "/comment", method = RequestMethod.GET)
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/save")
	public ResponseEntity<CommentDto> save(@RequestBody CommentDto commentDto,
			@RequestParam(value = "userId") Integer userId, @RequestParam(value = "postId") Integer postId) {
		CommentDto commentDto2 = this.commentService.save(commentDto, userId, postId);
		return new ResponseEntity<CommentDto>(commentDto2, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<?> delete(@PathVariable("commentId") Integer commentId) {
		this.commentService.delete(commentId);
		return new ResponseEntity<String>("Deleted Comment with id  {" + commentId+" }", HttpStatus.OK);
	}
}
