package com.team4.shoppingmall.rent_prod_stock;

import java.util.List;

public interface RentProdStockDAOInterface {
	
	//���� �ɼ� ��ǰ�� �뿩 ���id ã��
	public RentProdStockDTO selectRentStockByProdId(String prod_id);

	// �뿩��ǰ��
	public RentProdStockDTO selectById(Integer stock_id);

	// �뿩��ǰ���
	public List<RentProdStockDTO> selectAll();

	// �뿩��ǰ���
	public int rentProdInsert(RentProdStockDTO rentprod);

	// �뿩��ǰ����
	public int rentProdUpdate(Integer stock_id);

	// �뿩��ǰ����
	public int rentProdDelete(Integer stock_id);

}
