package com.lifeng.dao;

import java.util.List;

import com.lifeng.entity.Comment;


public interface CommentDao {
	

	// �Է�װidѰ������
	public List<Comment> findComment(int dressid);
}
