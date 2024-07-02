package com.team4.shoppingmall.rent_prod_stock;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.shoppingmall.prod.ProductNewVO;

@Service
public class RentProdStockService {

	@Autowired
	RentProdStockDAOInterface rentProdStockDAO;

	//���� �ɼ� ��ǰ�� �뿩 ���id ã��
	public Map<String,String> selectRentStockByProdId(String prod_id, String optionString) {
		return rentProdStockDAO.selectRentStockByProdId(prod_id, optionString);
	};
	//��ǰ�� �뿩 ���id ��ȸ
	public List<RentProdStockDTO> selectRentStockByProdId2(String prod_id){
		return rentProdStockDAO.selectRentStockByProdId2(prod_id);
	}
	//�뿩 ������ �뿩 ���� ������Ʈ
	public int rentProdStockUpdate(ProductNewVO prodVO) {
		return rentProdStockDAO.rentProdStockUpdate(prodVO);
	};
	
	//�뿩 ��ǰ �ɼǺ� ��� ��ȸ
	public List<RentProdStockDTO> selectRpsOptionByProdId(String prod_id){
		return rentProdStockDAO.selectRpsOptionByProdId(prod_id);
	};
	
	// �뿩��ǰ��
	public RentProdStockDTO selectById(String r_stock_id) {
		return rentProdStockDAO.selectById(r_stock_id);
 	}
	
	public Integer findMaxStockNumber(String prod_id) {
		return rentProdStockDAO.findMaxStockNumber(prod_id);
	}
	
	public List<RentProdStockListDTO> findRentStockList(String member_id){
		System.out.println("service ���� �����");
		return rentProdStockDAO.findRentStockList(member_id);
	}

	// �뿩��ǰ��� 
	public List<RentProdStockDTO> selectAll() {
		return rentProdStockDAO.selectAll();	
 	}

	// �뿩��ǰ��� 
	public int rentProdInsert(RentProdStockDTO rentprod) {
		return rentProdStockDAO.rentProdInsert(rentprod);	
 	}

	// �뿩��ǰ���� 
	public int rentProdUpdate(RentProdStockDTO rentprod) {
		return rentProdStockDAO.rentProdUpdate(rentprod);
 	}
	
	public int rentStockUpdate(RentProdStockDTO rentprod) {
		return rentProdStockDAO.rentStockUpdate(rentprod);
	}

	// �뿩��ǰ���� 
	public int rentProdDelete(String r_stock_id) {
		return rentProdStockDAO.rentProdDelete(r_stock_id);
 	}

}