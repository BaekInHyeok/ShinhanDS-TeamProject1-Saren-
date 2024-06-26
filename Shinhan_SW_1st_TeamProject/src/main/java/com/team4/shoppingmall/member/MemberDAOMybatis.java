package com.team4.shoppingmall.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOMybatis implements MemberDAOInterface {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.saren.member.";
	
	@Override
	public MemberDTO selectById(String member_id) {
		return sqlSession.selectOne(namespace+"selectById", member_id);
	}

	@Override
	public List<MemberDTO> selectAll() {
		return sqlSession.selectList(namespace+"selectAll");
	}

	@Override
	public int memberInsert(MemberDTO member) {
		return sqlSession.insert(namespace+"memberInsert", member);
	}

	@Override
	public int memberUpdate(MemberDTO member) {
		return sqlSession.update(namespace+"memberUpdate", member);
	}

	@Override
	public int memberDelete(String member_id) {
		return sqlSession.delete(namespace+"memberDelete", member_id);
	}
	
	@Override
	public List<MemberDTO> selectByCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberDTO> selectBySeller() {
		return sqlSession.selectList(namespace+"selectBySeller");
	}
	
	@Override
	public List<MemberDTO> selectBySeller_authority() {
		return sqlSession.selectList(namespace+"selectBySeller_authority");
	}
	
	@Override
	public MemberDTO selectBySeller_info(String member_id) {
		return sqlSession.selectOne(namespace+"selectBySeller_info", member_id);
	}

}
