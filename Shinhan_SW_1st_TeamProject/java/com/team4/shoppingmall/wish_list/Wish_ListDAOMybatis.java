package com.team4.shoppingmall.wish_list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Wish_ListDAOMybatis implements Wish_ListDAOInterface {

	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.saren.wish_list.";
	
	@Override
	public Wish_ListDTO selectByMemberIdAndProdId(String member_id, Integer prod_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", member_id);
		map.put("prod_id", prod_id);
		return sqlSession.selectOne(namespace+"selectByMemberIdAndProdId", map);
	}
	
	@Override
	public List<Wish_ListDTO> selectByMemberId(String member_id) {
		return sqlSession.selectList(namespace+"selectByMemberId", member_id);
	}
	
	@Override
	public List<Wish_ListDTO> selectByProdId(Integer prod_id) {
		return sqlSession.selectList(namespace+"selectByProdId", prod_id);
	}

	@Override
	public List<Wish_ListDTO> selectAll() {
		return sqlSession.selectList(namespace+"selectAll");
	}

	@Override
	public int wish_listInsert(Wish_ListDTO wish_list) {
		return sqlSession.insert(namespace+"wish_listInsert", wish_list);
	}

	@Override
	public int wish_listUpdate(Wish_ListDTO wish_list) {
		return sqlSession.update(namespace+"wish_listUpdate", wish_list);
	}

	@Override
	public int wish_listDelete(String member_id, Integer prod_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_id", member_id);
		map.put("prod_id", prod_id);
		return sqlSession.delete(namespace+"wish_listDelete", map);
	}
}
