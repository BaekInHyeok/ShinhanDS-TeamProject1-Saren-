package com.team4.shoppingmall.rent;

import java.util.List;

public interface RentDAOInterface {
	
	// �뿩��
	public RentDTO selectById(Integer rental_code);
	
	// �뿩���
	public List<RentDTO> selectAll();
	
	// �뿩�ϱ�
	public int rentInsert(RentDTO rent);
	
	// �뿩���� ����
	public int rentUpdate(Integer rental_code);
	
}
