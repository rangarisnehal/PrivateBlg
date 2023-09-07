package com.snehal.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snehal.blog.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
