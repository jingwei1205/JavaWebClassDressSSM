package com.lifeng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeng.dao.CommentDao;
import com.lifeng.entity.Comment;

@Service("commentService")
	public class CommentServiceImpl implements CommentService{
		@Autowired
		private CommentDao commentDao;
			
		// 以服装id寻找评论	
		public CommentDao getCommentDao() {
			return commentDao;
		}

		public void setCommentDao(CommentDao commentDao) {
			this.commentDao = commentDao;
		}

		@Override
		public List<Comment> findComment(int dressid) {
			return commentDao.findComment(dressid);
		}
	}
