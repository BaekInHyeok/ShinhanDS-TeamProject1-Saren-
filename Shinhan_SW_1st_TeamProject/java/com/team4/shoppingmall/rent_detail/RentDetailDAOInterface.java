package com.team4.shoppingmall.rent_detail;

import java.util.List;

public interface RentDetailDAOInterface {
	
	// �뿩�� ��
	public RentDetailDTO selectById(Integer rentdetail_id);
	
	// �뿩�� ���
	public List<RentDetailDTO> selectAll();
	
	// �뿩�� ����
	public int rentDetailInsert(RentDetailDTO rentdetail);
	
	// �뿩�� ����
	public int rentDetailUpdate(RentDetailDTO rentdetail);
	
	// �뿩�� ����
//	public int rentDetailDelete(Integer rentdetail_id);
	
}
