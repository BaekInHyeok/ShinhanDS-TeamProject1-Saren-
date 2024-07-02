package com.team4.shoppingmall.customer;

import java.util.List;

public interface CustomerDAOInterface {
	
	// ����
	public CustomerDTO selectById(Integer rental_code);
	
	// �����
	public List<CustomerDTO> selectAll();
	
	// �����
	public int customerInsert(CustomerDTO customer);
	
	// ������
	public int customerUpdate(Integer rental_code);
	
}
