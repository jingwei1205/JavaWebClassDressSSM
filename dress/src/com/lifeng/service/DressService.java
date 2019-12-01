package com.lifeng.service;

import com.lifeng.entity.Dress;
import com.lifeng.entity.PageBean;

public interface DressService {
	// ��id��ȡ��װ
	public Dress findDressById(int id);

	// ��ȡ���з�װ����ҳ
	public PageBean findDressPage(int currentPage, int pageSize, String dressname, String category,
			double minprice, double maxprice);

	public void updateDress(Dress dress);

}
