package com.team4.shoppingmall.order_prod;

import java.util.List; 

public interface OrderProdDAOInterface {
	
	
	// �ֹ���
	public OrderProdDTO selectById(Integer order_id);
	
	// �ֹ����
	public List<OrderProdDTO> selectAll();
	
	// �ֹ�����
	public int orderprodInsert(OrderProdDTO orderprod); 
	
	// �ֹ�����
	public int orderprodUpdate(OrderProdDTO orderprod);


}
