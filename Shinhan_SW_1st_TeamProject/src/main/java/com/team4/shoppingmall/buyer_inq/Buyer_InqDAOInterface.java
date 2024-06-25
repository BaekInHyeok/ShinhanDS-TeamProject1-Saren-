package com.team4.shoppingmall.buyer_inq;

import java.util.List;
import java.util.Map;

public interface Buyer_InqDAOInterface {
	
	//��ǰID�� ��ü ���� ��� ��ȸ 
	public List<Buyer_InqDTO> selectByProdId(String prod_id);
	
	public Buyer_InqDTO selectByInqId(Integer buyer_inq_id);
	
	public List<Buyer_InqDTO> selectByMemberId(String member_id);

	public List<Buyer_InqDTO> selectAll();
	
//	public List<MemberDTO> selectByCondition();
	
	//insert Ÿ�� ����
	public int buyer_inqInsert(Map<String,String> buyer_inq_map);
	
	public int buyer_inqUpdate(Buyer_InqDTO buyer_inq);
	
	public int buyer_inqDelete(Integer buyer_inq_id);
}
