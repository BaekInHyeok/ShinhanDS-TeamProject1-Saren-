package com.team4.shoppingmall.rent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService {
	
	@Autowired
	RentDAOInterface rentDAO;
	
	// �뿩��
	public RentDTO selectById(Integer rental_code) {
		return rentDAO.selectById(rental_code);
	}

	// �뿩���
	public List<RentDTO> selectAll() {
		return rentDAO.selectAll();
	}

	// �뿩�ϱ�
	public int rentInsert(RentDTO rent) {
		return rentDAO.rentInsert(rent);
	}

	// �뿩���� ����
	public int rentUpdate(Integer rental_code) {
		return rentDAO.rentUpdate(rental_code);
	}

}