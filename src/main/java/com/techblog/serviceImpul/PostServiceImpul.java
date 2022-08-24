package com.techblog.serviceImpul;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.techblog.entity.Category;
import com.techblog.entity.Post;
import com.techblog.entity.User;
import com.techblog.exceptions.ResourceNotFoundException;
import com.techblog.helper.PostResponse;
import com.techblog.payload.PostDto;
import com.techblog.repository.CategoryRepositiry;
import com.techblog.repository.PostRepository;
import com.techblog.repository.UserRepository;
import com.techblog.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class PostServiceImpul implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepositiry categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer CategoryId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userId));
		
		Category category = this.categoryRepository.findById(CategoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", CategoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setImageName("Default.png");
		post.setPost_date(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savepodt = this.postRepository.save(post);

		PostDto map = this.modelMapper.map(savepodt, PostDto.class);

		return map;
	}

	@Override
	public PostDto updatePost(Integer id, PostDto postDtoo) {
		PostDto postDto = this.getbyId(id);
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setPostTitle(postDtoo.getPostTitle());
		post.setPostContent(postDtoo.getPostContent());

		Post post2 = this.postRepository.save(post);
		PostDto postDto2 = this.modelMapper.map(post2, PostDto.class);
		return postDto2;
	}

	@Override
	public void deletePost(Integer postid) {

		this.postRepository.deleteById(postid);

	}

	@Override
	public PostResponse getPosts(Integer pageSize, Integer PageNumber) {

		Pageable pageRequest = PageRequest.of(PageNumber, pageSize);

		Page<Post> postList = this.postRepository.findAll(pageRequest);

		List<Post> content = postList.getContent();

		List<PostDto> postDtoList = content.stream().map(i -> this.modelMapper.map(i, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtoList);
		postResponse.setPageNumber(postList.getNumber());
		postResponse.setPageSize(postList.getSize());
		postResponse.setTotalPages(postList.getTotalPages());
		postResponse.setLastPage(postList.isLast());
		postResponse.setPageElements(postList.getTotalElements());

		return postResponse;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryID", categoryId));

		List<Post> categoryList = this.postRepository.findByCategory(category);
		List<PostDto> postList = categoryList.stream().map(i -> this.modelMapper.map(i, PostDto.class))
				.collect(Collectors.toList());

		return postList;
	}

	@Override
	public PostDto getbyId(Integer id) {
		Post throw1 = this.postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostID", id));
		PostDto map = this.modelMapper.map(throw1, PostDto.class);
		return map;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

		List<Post> listPosts = this.postRepository.findByUser(user);

		List<PostDto> postList = listPosts.stream().map(i -> this.modelMapper.map(i, PostDto.class))
				.collect(Collectors.toList());
		return postList;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
	
		List<Post> ListOfPosts = this.postRepository.findByPostTitleContaining(keyword);
		List<PostDto> listOfPostDto = ListOfPosts.stream().map(posts->this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
		
		
		return listOfPostDto;
	}

	@Override
	public PostResponse getSorted(Integer pageNumber, Integer pageSize, String sortedBy,String dir) {
		Pageable pageble=null;
		if(dir.equalsIgnoreCase("asc")) {
			pageble	 = PageRequest.of(pageNumber, pageSize, Sort.by(sortedBy).ascending());
		}else if(dir.equalsIgnoreCase("desc")){
			pageble = PageRequest.of(pageNumber, pageSize, Sort.by(sortedBy).descending());
		}else {
			pageble	 = PageRequest.of(pageNumber, pageSize, Sort.by(sortedBy).ascending());
		}
	



		Page<Post> postList = this.postRepository.findAll(pageble);
		
	
		List<Post> content = postList.getContent();
		
		List<PostDto> postDtoList = content.stream().map(posts->this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtoList);
		postResponse.setPageNumber(postList.getNumber());
		postResponse.setPageSize(postList.getSize());
		postResponse.setTotalPages(postList.getTotalPages());
		postResponse.setLastPage(postList.isLast());
		postResponse.setPageElements(postList.getTotalElements());

		return postResponse;

	}

	
	
	public List<PostDto> getPosts() {
		List<Post> postList = this.postRepository.findAll();
				List<PostDto> postDtoList = postList.stream().map(i -> this.modelMapper.map(i, PostDto.class)).collect(Collectors.toList());

				return postDtoList;
	}

	@Override
	public List<PostDto> searchContent(String keyword) {
String search="%"+keyword+"%";
		List<Post> ListOfPosts = this.postRepository.findByContent(search);
		List<PostDto> listOfPostDto = ListOfPosts.stream().map(posts->this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
		
		
		return listOfPostDto;
	}

}
