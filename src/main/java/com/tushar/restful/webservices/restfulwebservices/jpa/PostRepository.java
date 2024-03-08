package com.tushar.restful.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.restful.webservices.restfulwebservices.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
