package com.lifeng.service;

import java.util.List;

import com.lifeng.entity.Cart;




public interface CartService {
	
	// ��ȡ�û������й��ﳵ
	public List<Cart> findCartByUserId(int userid);
	
	// Ѱ�ҹ��ﳵ
	public Cart findCart(int dressid, int userid);
	
	// �Թ��ﳵid��Ĺ��ﳵ
	public Cart findCartByCartId(int id);
	
	// ɾ���������ﳵ
	public void removeCart(int id) ;
	
	// ��ӵ����ﳵ
	public void addCart(Cart cart);
	
	// �޸Ĺ��ﳵ��װ����
	public void updateCart(Cart cart);

}
