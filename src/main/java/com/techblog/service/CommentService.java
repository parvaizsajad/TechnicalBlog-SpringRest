package com.techblog.service;

import com.techblog.payload.CommentDto;

public interface CommentService {

	CommentDto save(CommentDto commentDto, Integer userId, Integer postId);

	void delete(Integer commentId);

}
