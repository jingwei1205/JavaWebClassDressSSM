package com.lifeng.dao;

import java.util.List;

import com.lifeng.entity.Comment;


public interface CommentDao {
	

	// 以服装id寻找评论
	public List<Comment> findComment(int dressid);
}
