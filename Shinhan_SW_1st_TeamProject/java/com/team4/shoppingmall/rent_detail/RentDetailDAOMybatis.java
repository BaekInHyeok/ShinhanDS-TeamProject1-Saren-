package com.team4.shoppingmall.rent_detail;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team4.shoppingmall.coupon.CouponDTO;

@Repository
public class RentDetailDAOMybatis implements RentDetailDAOInterface {

	@Autowired
	SqlSession sqlSession;

	String namespace = "com.saren.rentdetail.";

	// �뿩�� ��
	@Override
	public RentDetailDTO selectById(Integer rentdetail_id) {
		return sqlSession.selectOne(namespace+"selectById", rentdetail_id);
	}

	// �뿩�� ���
	@Override
	public List<RentDetailDTO> selectAll() {
		return sqlSession.selectList(namespace+"selectAll");
	}

	// �뿩�� ����
	@Override
	public int rentDetailInsert(RentDetailDTO rentdetail) {
		return sqlSession.insert(namespace+"rentDetailInsert", rentdetail);
	}
	
	// �뿩�� ����
	@Override
	public int rentDetailUpdate(RentDetailDTO rentdetail) {
		return sqlSession.update(namespace+"rentDetailInsert", rentdetail);
	}


}
