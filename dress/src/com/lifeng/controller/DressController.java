package com.lifeng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lifeng.entity.Comment;
import com.lifeng.entity.Dress;
import com.lifeng.entity.PageBean;
import com.lifeng.service.CommentService;
import com.lifeng.service.DressService;

@Controller
public class DressController {
	@Autowired
	private DressService dressService;

	public DressService getDressService() {
		return dressService;
	}

	public void setDressService(DressService dressService) {
		this.dressService = dressService;
	}
	
	@Autowired
	private CommentService commentService;

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	

	@RequestMapping("/page")
	public ModelAndView page(int currentPage, String dressname, String category, double minprice, double maxprice) {
		ModelAndView mv = new ModelAndView();
		// ��ҳ��ѯ��������PageBean����
		try {
			int pageSize = 4;// �ݶ�ÿҳ��ʾ4��
			PageBean pb = dressService.findDressPage(currentPage, pageSize, dressname, category, minprice, maxprice);
			mv.addObject("pb", pb);
			mv.setViewName("list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/showDress")
	public ModelAndView showDress(int id) {
		ModelAndView mv=new ModelAndView();
		Dress dress=dressService.findDressById(id);
		List<Comment> evlist=commentService.findComment(id);
		mv.addObject("dress",dress);
		mv.addObject("evlist",evlist);
		mv.setViewName("dress");
		return mv;		
	}

}
