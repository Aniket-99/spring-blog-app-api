package com.api.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.blog.entities.Comment;
import com.api.blog.entities.Post;
import com.api.blog.exceptions.ResourceNotFoundException;
import com.api.blog.payloads.CommentDto;
import com.api.blog.repositories.CommentRepo;
import com.api.blog.repositories.PostRepo;
import com.api.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);

		Comment savedComment = this.commentRepo.save(comment);

		return this.modelMapper.map(savedComment, CommentDto.class);

	}

	@Override
	public void deleteComment(int commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));
		this.commentRepo.delete(comment);
		
	}

}
