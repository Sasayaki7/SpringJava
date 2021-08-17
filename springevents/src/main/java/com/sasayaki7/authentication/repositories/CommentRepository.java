package com.sasayaki7.authentication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sasayaki7.authentication.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findAll();

}
