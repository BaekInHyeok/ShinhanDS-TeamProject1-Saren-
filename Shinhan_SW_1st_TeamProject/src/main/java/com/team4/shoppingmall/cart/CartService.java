package com.team4.shoppingmall.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	
	@Autowired
	CartDAOInterface cartDAO;
	
	//������ �ɼ� ��ǰ�� ���ID ��ȸ
	public String searchStockId(HashMap<String, String> map, String prod_id) {
		return cartDAO.searchStockId(map, prod_id);
	}
	
	//��ٱ��Ͽ� ���� ��ǰ�� �����ϴ��� ��ȸ
	public CartDTO selectCartBySellstock(Map<String,String> map) {
		return cartDAO.selectCartBySellstock(map);
	}
	
	public List<CartDTO> selectSellStockByMemberId(String member_id) {
		return cartDAO.selectSellStockByMemberId(member_id);
	}
	
	public List<CartDTO> selectRentStockByMemberId(String member_id) {
		return cartDAO.selectRentStockByMemberId(member_id);
	}
	
	public List<CartDTO> selectAllStockByMemberId(String member_id) {
		return cartDAO.selectAllStockByMemberId(member_id);
	}
	
	public CartDTO selectByCartId(Integer cart_id) {
		return cartDAO.selectByCartId(cart_id);
	}
	
	public List<CartDTO> selectAll() {
		return cartDAO.selectAll();
	}
	
	public int cartInsert(CartDTO cart) {
		return cartDAO.cartInsert(cart);
	}
	
	public int cartUpdate(CartDTO cart) {
		return cartDAO.cartUpdate(cart);
	}
	
	public int cartDelete(Integer cart_id) {
		return cartDAO.cartDelete(cart_id);
	}
	
	//��ٱ��� ��ǰ ���� ������Ʈ
	public int updateCartBySellstock(CartDTO cart) {
		return cartDAO.updateCartBySellstock(cart);
	}
	
}