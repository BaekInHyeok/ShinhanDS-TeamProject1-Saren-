package com.team4.shoppingmall.order_prod;

import java.util.List;
import java.util.Map; 

public interface OrderProdDAOInterface {
	
	
	// �ֹ���
	public OrderProdDTO selectById(Integer order_id);
	
	// �ֹ����
	public List<OrderProdDTO> selectAll();
	
	// �ֹ�����
	public int orderprodInsert(OrderProdDTO orderprod); 
	
	// �ֹ�����
	public int orderprodUpdate(OrderProdDTO orderprod);
	
	// orderlist.jsp�� ����� ��ǰ��, �귣��, �ɼ�, ��ǰ����, �̹���URL 
	public List<Map<String, Object>> selectById2(Integer order_id);
	


}
