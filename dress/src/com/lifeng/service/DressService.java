package com.lifeng.service;

import com.lifeng.entity.Dress;
import com.lifeng.entity.Order;
import com.lifeng.entity.PageBean;

import java.util.List;
import java.util.Map;

public interface DressService {
	// 以id获取服装
	public Dress findDressById(int id);

	// 获取所有服装并分页
	public PageBean findDressPage(int currentPage, int pageSize, String dressname, String category,
			double minprice, double maxprice);

	public void updateDress(Dress dress);
	public Dress searchDress(int id);
	public int deleteDress(int id);
	public int updateInfo(int dressid,
						  String dressname,String category,String price,
						  String description);
	public int addDress(String dressname,String category,String price,
						String quantity,String description);

}
