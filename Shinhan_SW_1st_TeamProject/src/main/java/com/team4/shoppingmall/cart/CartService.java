package com.team4.shoppingmall.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team4.shoppingmall.prod.ProductNewVO;

@Service
public class CartService {
	
	@Autowired
	CartDAOInterface cartDAO;
	
	//������ �ɼ� ��ǰ�� ���ID ��ȸ
	public String searchStockId(HashMap<String, String> map, String prod_id) {
		return cartDAO.searchStockId(map, prod_id);
	}
	
	//��ٱ��Ͽ� ���� ��ǰ�� �����ϴ��� ��ȸ
//	public CartDTO selectCartBySellstock(Map<String,String> map) {
//		return cartDAO.selectCartBySellstock(map);
//	}
	public CartDTO selectCartBySellstock(CartDTO cartDTO) {
		return cartDAO.selectCartBySellStock(cartDTO);
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
	
	//�ǸŻ�ǰ ��ٱ��� insert��
	@Transactional
	public int cartInsert(ProductNewVO prodVO, String member_id) {
	
		CartDTO cartDTO = new CartDTO();
		cartDTO.setMember_id(member_id);
		cartDTO.setS_stock_id(prodVO.getS_stock_id());
		cartDTO.setCart_amount(prodVO.getOrder_num()); //����
		
		//�̹� �ִ��� ��ȸ
		CartDTO isExistsSellCart = cartDAO.selectCartBySellStock(cartDTO);
		
		if(isExistsSellCart != null){
			
			cartDTO.setCart_id(isExistsSellCart.getCart_id());
			//�ߺ� ��ǰ�� ���� + ������Ʈ
			int updateResult = cartDAO.updateCartBySellStock(cartDTO);
			return updateResult;
			
		}else {
			//��ٱ��� ����
			int result = cartDAO.cartInsert(cartDTO);
			return result;
		}
	}
	//�뿩��ǰ ��ٱ��� insert��
	public int cartRentProductInsert(ProductNewVO prodVO, String member_id) {
		
		CartDTO cartDTO = new CartDTO();
		cartDTO.setMember_id(member_id);
		cartDTO.setS_stock_id(prodVO.getS_stock_id());
		cartDTO.setCart_amount(prodVO.getRent_num());//����
		
		//�̹� �ִ��� ��ȸ
		CartDTO isExistsRentCart = cartDAO.selectCartByRentStock(cartDTO);
		
		if(isExistsRentCart != null){
			
			cartDTO.setCart_id(isExistsRentCart.getCart_id());
			//�ߺ� ��ǰ�� ���� + ������Ʈ
			int updateResult = cartDAO.updateCartByRentStock(cartDTO);
			return updateResult;
			
		}else {
			//��ٱ��� ����
			int result = cartDAO.cartRentProductInsert(cartDTO);
			return result;
		}
	}

	//��ٱ��� ��ǰ ���� ������Ʈ
	public int updateCartBySellStock(CartDTO cart) {
		return cartDAO.updateCartBySellStock(cart);
	}
	
	public int cartUpdate(CartDTO cart) {
		return cartDAO.cartUpdate(cart);
	}
	
	public int cartDelete(Integer cart_id) {
		return cartDAO.cartDelete(cart_id);
	}
	
	
}