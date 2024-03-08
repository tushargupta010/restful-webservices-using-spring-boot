package com.tushar.restful.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tushar.restful.webservices.restfulwebservices.jpa.PostRepository;
import com.tushar.restful.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaController {

	private UserRepository userRepository;

	private PostRepository postRepository;

	public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@GetMapping("/jpa/users/{userId}")
	public EntityModel<User> retrieveUser(@PathVariable int userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isEmpty())
			throw new UserNotFoundException("user id : " + userId + " not found.");

		EntityModel<User> entityModel = EntityModel.of(user.get());

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}

	@DeleteMapping("/jpa/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userRepository.deleteById(userId);
	}

	@GetMapping("/jpa/users/{userId}/posts")
	public List<Post> retrieveAllPostsForUser(@PathVariable int userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isEmpty())
			throw new UserNotFoundException("user id : " + userId + " not found.");

		return user.get().getPosts();
	}

	@PostMapping("/jpa/users/{userId}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int userId, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isEmpty())
			throw new UserNotFoundException("user id : " + userId + " not found.");

		post.setUser(user.get());

		Post savedPost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
