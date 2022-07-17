package com.api.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.blog.payloads.ApiResponse;
import com.api.blog.payloads.PostDto;
import com.api.blog.services.impl.PostServiceImpl;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostServiceImpl postServiceImpl;

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable("postId") int postId) {
		PostDto post = this.postServiceImpl.getPostById(postId);

		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts() {
		List<PostDto> allPosts = this.postServiceImpl.getAllPosts();

		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable("postId") int postId, @RequestBody PostDto postDto) {
		PostDto updatedPost = this.postServiceImpl.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") int postId) {
		this.postServiceImpl.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
	}

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") int userId,
			@PathVariable("categoryId") int categoryId) {

		PostDto savedPost = this.postServiceImpl.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(savedPost, HttpStatus.CREATED);

	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") int userId) {

		List<PostDto> posts = this.postServiceImpl.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") int categoryId) {
		List<PostDto> posts = this.postServiceImpl.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

}