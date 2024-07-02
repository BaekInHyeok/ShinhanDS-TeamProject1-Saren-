package com.team4.shoppingmall.rent_detail;

import java.util.List;

public interface RentDetailDAOInterface {
	
	// �뿩�� ��
	public RentDetailDTO selectById(Integer rentdetail_id);
	
	// �뿩�� ���
	public List<RentDetailDTO> selectAll();
	
	public List<RentDetailDTO> selectBySellerID(String member_id);
	
	// �뿩�� ����
	public int rentDetailInsert(RentDetailDTO rentdetail);
	
	// �뿩�� ����
	public int rentDetailUpdate(RentDetailDTO rentdetail);

	public int rentDetailStatusUpdate(RentDetailDTO rentdetail);
	
	// �뿩�� ����
	public int rentDetailDelete(int rentdetail_id);

	// �뿩ID�� �뿩�� ���
	public List<RentDetailDTO> selectByRental_code(int rental_code);
	
}
