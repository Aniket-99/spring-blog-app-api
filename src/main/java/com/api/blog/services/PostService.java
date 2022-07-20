package com.api.blog.services;

import java.util.List;

import com.api.blog.payloads.PostDto;
import com.api.blog.payloads.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto, int userId, int categoryId);

	public PostDto updatePost(PostDto postDto, int postId);

	public void deletePost(int postId);

	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);

	public PostDto getPostById(int id);

	public List<PostDto> getPostByCategory(int categoryId);

	public List<PostDto> getPostByUser(int userId);

	public List<PostDto> searchPost(String keyword);

}
