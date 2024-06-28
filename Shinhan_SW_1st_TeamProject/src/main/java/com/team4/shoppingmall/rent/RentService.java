package com.team4.shoppingmall.rent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team4.shoppingmall.rent_detail.RentDetailDAOInterface;
import com.team4.shoppingmall.rent_detail.RentDetailDTO;
import com.team4.shoppingmall.util.DateUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentService {

	final RentDAOInterface rentDAO;
	final RentDetailDAOInterface rentDetailDAO;

	public int searchRentId() {
		return rentDAO.searchRentId();
	}

	// �뿩��
	public RentDTO selectById(Integer rental_code) {
		return rentDAO.selectById(rental_code);
	}

	// �뿩���
	public List<RentDTO> selectAll() {
		return rentDAO.selectAll();
	}

	// �뿩�ϱ� (�뿩insert, �뿩ID ã��, �뿩�� insert) ->�� ��� �� �ϳ��� �����ϸ� rollback
	@Transactional
	public int rentInsert(String member_id, HashMap<String, String> map, Map<String, String> rentProductStockCheck) {

		RentDTO rent = new RentDTO();

		rent.setRent_start_date(DateUtil.getSQLDate(map.get("rent_start_date")));
		rent.setRent_end_date(DateUtil.getSQLDate(map.get("rent_end_date")));
		rent.setMember_id(member_id);
		rent.setTotal_rent_price(Integer.parseInt(map.get("total_amount"))); //�� �뿩����
		
		int result = rentDAO.rentInsert(rent);
		System.out.println("rentInsert result = " + result);
		int rentId = rentDAO.searchRentId();
		
		// 3.�ɼǿ� �ش��ϴ� ��ǰ�� �뿩���ID(r_stock_id) ���ϰ� �뿩�� ����
		String rentStockId = rentProductStockCheck.get("R_STOCK_ID");

		RentDetailDTO rentDetailDTO = new RentDetailDTO();

		//�뿩������ �ֱ� (�� �뿩���� ����) - ������
		//int rentPrice = Integer.parseInt(map.get("total_amount")); 
		//int rentNum = Integer.parseInt(map.get("rent_prod_quantity"));
		//int detailPrice = rentPrice / rentNum;   //null
		
		//rentDetailDTO.setRent_product_price(detailPrice); //�뿩 ���� 30,000�����ϴµ� null
		
		rentDetailDTO.setR_stock_id(rentStockId); // �뿩���id
		rentDetailDTO.setRent_num(Integer.parseInt(map.get("rent_prod_quantity"))); // �뿩 �ֹ� ����
		
		
		rentDetailDTO.setRental_code(rentId); // �뿩id 
		result = rentDetailDAO.rentDetailInsert(rentDetailDTO);
		System.out.println("rentDetailInsert result = " + result);

		return result;
	}

	// �뿩���� ����
	public int rentUpdate(Integer rental_code) {
		return rentDAO.rentUpdate(rental_code);
	}

}