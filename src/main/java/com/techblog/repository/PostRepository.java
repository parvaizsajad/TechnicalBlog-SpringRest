package com.techblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techblog.entity.Category;
import com.techblog.entity.Post;
import com.techblog.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByPostTitleContaining(String title);
	
	@Query("select p from Post p where p.postContent like:keyword")
	List<Post> findByContent(@Param("keyword")String title);
	


}
