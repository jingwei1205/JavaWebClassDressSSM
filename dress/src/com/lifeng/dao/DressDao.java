package com.lifeng.dao;

import java.util.List;
import java.util.Map;

import com.lifeng.entity.Dress;

public interface DressDao {
	// ��id��ȡ��װ
	public Dress findDressById(int id);

	// ��ȡ����ʱװ����ҳ
	public List<Dress> findDressPage(Map<String, Object> map);

	public void updateDress(Dress dress);

	public int findDressCount(Map<String, Object> map);

	public Dress searchDress(int id);
	public int deleDress(int id);
	public int updateInfo(int dressid,
						  String dressname,String category,String price,
						  String description);
	public int addDress(String dressname,String category,String price,
						String quantity,String description);

}
