package com.lifeng.dao;

import java.util.List;

import com.lifeng.entity.Cart;




public interface CartDao {
	
	// ��ȡĳ���û������й��ﳵ
	public List<Cart> findCartByUserId(int userid);
	
	// ��ȡ�û���ĳ����Ʒ�Ĺ��ﳵ
	public Cart findCart(int dressid, int userid);
	
	// �Թ��ﳵidѰ����Ʒ
	public Cart findCartByCartId(int id);
		
	// ɾ������
	public void removeCart(int id);
	
	// ��ӵ����ﳵ
	public void addCart(Cart cart);
	
	
	// �޸Ĺ��ﳵ��װ����
	public void updateCart(Cart cart);


}
