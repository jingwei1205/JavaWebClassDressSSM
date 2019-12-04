package com.lifeng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lifeng.entity.Order;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lifeng.entity.Comment;
import com.lifeng.entity.Dress;
import com.lifeng.entity.PageBean;
import com.lifeng.service.CommentService;
import com.lifeng.service.DressService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		// 分页查询，并返回PageBean对象
		try {
			int pageSize = 4;// 暂定每页显示4条
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
		ModelAndView mv = new ModelAndView();
		Dress dress = dressService.findDressById(id);
		List<Comment> evlist = commentService.findComment(id);
		mv.addObject("dress", dress);
		mv.addObject("evlist", evlist);
		mv.setViewName("dress");
		return mv;
	}

	@RequestMapping("/DelePro")
	@ResponseBody
	public Object searchDress(HttpSession session, HttpServletResponse response, String type, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(id + "" + type);
		int intId = Integer.parseInt(id);
		if ("so".equals(type)) {
			Dress dress = dressService.searchDress(intId);
			if (dress != null) {
				map.put("state", "true");
				map.put("id", intId);
				map.put("dressname", dress.getDressname());
				map.put("category", dress.getCategory());
				map.put("price", dress.getPrice());
				map.put("description", dress.getDescription());
				map.put("sales", dress.getSales());
			} else {
				map.put("state", "false");
			}
		} else {
			int changeStatus = dressService.deleteDress(intId);
			if (changeStatus != 0) {
				map.put("state", "true");
			} else {
				map.put("state", "false");
			}
		}
		return map;
	}

	@RequestMapping("/modifyDress")
	@ResponseBody
	public Object modifyDress(HttpServletRequest request, HttpServletResponse response){
		JSONObject info=new JSONObject();
		String dressid=request.getParameter("dressid");
		String dressname =request.getParameter("dressname");
		String category =request.getParameter("category");
		String price=request.getParameter("price");
		String description=request.getParameter("description");
//		System.out.println(dressid+dressname+category);
		Map<String, Object> map=new HashMap<String, Object> ();
		int intId=Integer.parseInt(dressid);
//		System.out.println(intId+""+dressname+category+price+description);
		int changeStatus = dressService.updateInfo(intId,dressname,category,price,description);
//		System.out.println("num:"+changeStatus);
		if(changeStatus !=0){
			map.put("state", "true");
		}
		else {
		    map.put("state","false");
		}
		return map;
	}

	@RequestMapping("/addDress")
	@ResponseBody
	public Object addDress(HttpServletRequest request, HttpServletResponse response){
		JSONObject info=new JSONObject();
		String dressname =request.getParameter("dressname");
		String category =request.getParameter("category");
		String price=request.getParameter("price");
		String quantity = request.getParameter("quantity");
		String description=request.getParameter("description");
//		System.out.println(quantity+dressname+category);
		int changeStatus = dressService.addDress(dressname,category,price,quantity,description);
		Map<String, Object> map=new HashMap<String, Object> ();
		if(changeStatus !=0){
			map.put("state", "true");
		}
		else {
			map.put("state","false");
		}
		return map;
	}

}
