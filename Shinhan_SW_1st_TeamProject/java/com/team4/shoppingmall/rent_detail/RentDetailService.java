package com.team4.shoppingmall.rent_detail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentDetailService {
	
	@Autowired
	RentDetailDAOInterface rentDetailDAO;
	
	// �뿩�� ��
	public RentDetailDTO selectById(Integer rentdetail_id) {
		return rentDetailDAO.selectById(rentdetail_id);
	}

	// �뿩�� ���
	public List<RentDetailDTO> selectAll() {
		return rentDetailDAO.selectAll();
	}

	// �뿩�� ����
	public int rentDetailInsert(RentDetailDTO rentdetail) {
		return rentDetailDAO.rentDetailInsert(rentdetail);
	}
	
	// �뿩�� ���� 
	public int rentDetailUpdate(RentDetailDTO rentdetail) {
		return rentDetailDAO.rentDetailUpdate(rentdetail);
	}

}