package com.team4.shoppingmall.order_prod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProdService {

	@Autowired
	OrderProdDAOInterface orderprodDAO;

	// �ֹ���
	public OrderProdDTO selectById(Integer order_id) {
		return orderprodDAO.selectById(order_id);
	}

	// �ֹ����
	public List<OrderProdDTO> selectAll() {
		return orderprodDAO.selectAll();
	}

	// �ֹ�����
	public int orderprodInsert(OrderProdDTO orderprod) {
		return orderprodDAO.orderprodInsert(orderprod);
	}

	// �ֹ�����
	public int orderprodUpdate(OrderProdDTO orderprod) {
		return orderprodDAO.orderprodUpdate(orderprod);
	}
}