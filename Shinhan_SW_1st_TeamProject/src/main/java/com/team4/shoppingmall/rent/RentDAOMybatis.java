package com.team4.shoppingmall.rent;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team4.shoppingmall.rent_prod_stock.RentProdStockDTO;

@Repository
public class RentDAOMybatis implements RentDAOInterface {

	@Autowired
	SqlSession sqlSession;

	String namespace = "com.saren.rent.";

	// �뿩��
	@Override
	public RentDTO selectById(Integer rental_code) {
		return sqlSession.selectOne(namespace+"selectById", rental_code);
	}

	// �뿩���
	@Override
	public List<RentDTO> selectAll() {
		return sqlSession.selectList(namespace + "selectAll");
	}

	// �뿩�ϱ�
	@Override
	public int rentInsert(RentDTO rent) {
		return sqlSession.insert(namespace+"rentInsert", rent);
	} 

	// �뿩���� ����
	@Override
	public int rentUpdate(Integer rental_code) {
		return sqlSession.update(namespace+"rentUpdate", rental_code);
	} 

	// rentlist.jsp�� ����� ��ǰ��, �귣��, �ɼ�, ��ǰ����, �̹���URL
	@Override
	public Map<String, Object> selectById2(int rental_code) {
		return sqlSession.selectOne(namespace+"selectById2", rental_code); 
	}
	
	// rentlist.jsp����, �󼼻�ǰ �ɼ� ��½�, ��� �ɼ� ��������
	@Override
	public List<RentProdStockDTO> selectOptions() {
		return sqlSession.selectList(namespace+"selectOptions"); 

    }


}
