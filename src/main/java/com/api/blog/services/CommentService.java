package com.api.blog.services;

import com.api.blog.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto, int postId);

	public void deleteComment(int commentId);

}
