package com.lifeng.service;

import java.util.List;

import com.lifeng.entity.Comment;

public interface CommentService {
	public List<Comment> findComment(int dressid);
}
