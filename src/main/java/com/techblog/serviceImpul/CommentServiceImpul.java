package com.techblog.serviceImpul;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techblog.entity.Comment;
import com.techblog.entity.Post;
import com.techblog.entity.User;
import com.techblog.exceptions.ResourceNotFoundException;
import com.techblog.payload.CommentDto;
import com.techblog.repository.CommentRepo;
import com.techblog.repository.PostRepository;
import com.techblog.repository.UserRepository;
import com.techblog.service.CommentService;

@Service
public class CommentServiceImpul implements CommentService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	 private PostRepository postRepository;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto save(CommentDto commentDto, Integer userId, Integer postId) {
	
		User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User", userId));
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostID", postId));
		
		
		Comment commentmaper = this.modelMapper.map(commentDto, Comment.class);
		commentmaper.setUser(user.getName());
		commentmaper.setPost(post);
		Comment comment = this.commentRepo.save(commentmaper);
		CommentDto commentDtosave = this.modelMapper.map(comment, CommentDto.class);
		commentDtosave.setPostId(post.getPostId());
		
		return commentDtosave;
	}
	@Override
	public void delete(Integer commentId) {
		this.commentRepo.deleteById(commentId);
		
	}

}
