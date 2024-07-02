package com.team4.shoppingmall.order_prod;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderProdDAOMybatis implements OrderProdDAOInterface {

	//@Autowired
	SqlSession sqlSession;

	String namespace = "com.saren.orderprod.";

	// �ֹ���
	@Override
	public OrderProdDTO selectById(Integer order_id) {
		return sqlSession.selectOne(namespace+"", order_id);
	}

	// �ֹ����
	@Override
	public List<OrderProdDTO> selectAll() {
		return sqlSession.selectList(namespace+"selectAll");
	}

	// �ֹ�����
	@Override
	public int orderprodInsert(OrderProdDTO orderprod) {
		return sqlSession.insert(namespace+"orderprodInsert", orderprod);
	}

	// �ֹ�����
	@Override
	public int orderprodUpdate(OrderProdDTO orderprod) {
		return sqlSession.update(namespace+"orderprodUpdate", orderprod);
	}

}
