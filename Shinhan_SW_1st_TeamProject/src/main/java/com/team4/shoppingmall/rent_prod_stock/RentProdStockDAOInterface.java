package com.team4.shoppingmall.rent_prod_stock;

import java.util.List;

public interface RentProdStockDAOInterface {

	// �뿩��ǰ��
	public RentProdStockDTO selectById(String r_stock_id);

	public int findMaxStockNumber(String prod_id);
	
	public List<RentProdStockListDTO> findRentStockList(String member_id);
	
	// �뿩��ǰ���
	public List<RentProdStockDTO> selectAll();

	// �뿩��ǰ���
	public int rentProdInsert(RentProdStockDTO rentprod);

	// �뿩��ǰ����
	public int rentProdUpdate(String r_stock_id);

	// �뿩��ǰ����
	public int rentProdDelete(String r_stock_id);

	

}
