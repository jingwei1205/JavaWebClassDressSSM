package com.lifeng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lifeng.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeng.dao.DressDao;
import com.lifeng.entity.Dress;
import com.lifeng.entity.DressCondition;
import com.lifeng.entity.PageBean;

@Service("dressService")
public class DressServiceImpl implements DressService{
	@Autowired
	private DressDao dressDao;
	
	public DressDao getDressDao() {
		return dressDao;
	}

	public void setDressDao(DressDao dressDao) {
		this.dressDao = dressDao;
	}

	@Override
	public Dress findDressById(int id) {		
		return dressDao.findDressById(id);
	}

	@Override
	public PageBean findDressPage(int currentPage, int pageSize, String dressname, String category,
			double minprice, double maxprice) {		
		PageBean pb = new PageBean();		
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);
		try {
			Map<String,Object> map=new HashMap<String,Object>();		
			map.put("dressname", dressname);
			map.put("category", category);
			map.put("minprice", minprice);
			map.put("maxprice", maxprice);			
			int count  = dressDao.findDressCount(map);//得到总记录数	
			pb.setCount(count);
			int totalPage = (int)Math.ceil(count*1.0/pageSize); //求出总页数
			pb.setTotalPage(totalPage);			
			map.put("startRow", (currentPage-1)*pageSize);
			map.put("pageSize", pageSize);
			List<Dress> dresses= dressDao.findDressPage(map);	
			pb.setDresses(dresses);
			DressCondition dressCondition=new DressCondition(dressname, minprice, maxprice, category);
			System.out.println("dressCondition里面的category:"+dressCondition.getCategory());
			pb.setDressCondition(dressCondition);			
		} catch (Exception e) {
			e.printStackTrace();			
		}		
		return pb;

	}

	@Override
	public void updateDress(Dress dress) {
		dressDao.updateDress(dress);
		
	}

	@Override
	public Dress searchDress(int id){
		return dressDao.searchDress(id);
	}

	@Override
	public int deleteDress(int id){
		return dressDao.deleDress(id);
	}

	@Override
	public int updateInfo(int dressid,
						  String dressname,String category,String price,
						  String description)
	{return dressDao.updateInfo(dressid,dressname,category,price,description);}

	@Override
	public int addDress(String dressname,String category,String price,
						String quantity,String description){
		return dressDao.addDress(dressname, category,price,quantity,description);
	}

}
