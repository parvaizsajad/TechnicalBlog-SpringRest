package com.techblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techblog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
