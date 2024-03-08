package com.tushar.restful.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tushar.restful.webservices.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
